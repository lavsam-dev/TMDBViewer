package com.geekbrains.lavsam.TMDBViewer.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.graphics.get
import androidx.fragment.app.Fragment
import com.geekbrains.lavsam.TMDBViewer.BuildConfig
import com.geekbrains.lavsam.TMDBViewer.R
import com.geekbrains.lavsam.TMDBViewer.databinding.DetailFragmentBinding
import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail
import com.geekbrains.lavsam.TMDBViewer.model.dto.MovieDetailDTO
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.detail_fragment.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


private const val TMDB_API_KEY = BuildConfig.TMDB_API_KEY
private const val POSTER_DIMENSION = 200

class DetailsFragment : Fragment() {

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<MovieDetail>(BUNDLE_EXTRA)?.let {
//            populateData(movieDetailData = it)
            if (!TMDB_API_KEY.isNullOrEmpty()) {
                loadMovieDetail(movieDetailData = it)
            } else {
                Snackbar.make(binding.movieDetailMain, getString(R.string.absent_API_KEY), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateData(movieDetailData: MovieDetail) {
        with(binding) {
            movieName.text = movieDetailData.movie.name
            movieOverview.text = movieDetailData.overview
            movieGenre.text = movieDetailData.genre
            movieDirector.text = movieDetailData.director
            movieRuntime.text = movieDetailData.runtime
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadMovieDetail(movieDetailData: MovieDetail) {
        val urlMovieDetail = "https://api.themoviedb.org/3/movie/${movieDetailData.movie.id}?api_key=$TMDB_API_KEY"
//        val urlMoviePoster = "https://image.tmdb.org/t/p/w$POSTER_DIMENSION"
        try {
            val uriMovieDetail = URL(urlMovieDetail)
            val handler = Handler(Looper.myLooper()!!)
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uriMovieDetail.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    // преобразование ответа от сервера (JSON) в модель данных (WeatherDTO)
                    val movieDetailDTO: MovieDetailDTO =
                        Gson().fromJson(getLines(bufferedReader), MovieDetailDTO::class.java)
                    handler.post { displayMovieDetail(movieDetailDTO, movieDetailData) }

//                    urlConnection.disconnect()
//                    val uriMoviePoster = URL(urlMoviePoster + movieDetailDTO.poster_path)
//                    urlConnection = uriMoviePoster.openConnection() as HttpsURLConnection
//                    urlConnection.requestMethod = "GET"
//                    urlConnection.readTimeout = 10000
//                    binding.imageViewPoster.setImageBitmap(BitmapFactory.decodeStream(urlConnection.getInputStream()))

                } catch (e: Exception) {
                    Log.e("MovieDetail", "Fail connection", e)
                    e.printStackTrace()
                    //Обработка ошибки
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("MovieDetail", "Fail URI", e)
            e.printStackTrace()
            //Обработка ошибки
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun displayMovieDetail(movieDetailDTO: MovieDetailDTO, movieDetailData: MovieDetail) {
        with(binding) {
            movieDetailMain.show()
            loadingLayout.hide()

            movieName.text =  movieDetailDTO.original_title.toString()
            movieOverview.text = movieDetailDTO.overview.toString()
            movieRuntime.text = movieDetailDTO.runtime.toString()
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "movieDetail"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}