package com.geekbrains.lavsam.TMDBViewer.view

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.lavsam.TMDBViewer.BuildConfig
import com.geekbrains.lavsam.TMDBViewer.R
import com.geekbrains.lavsam.TMDBViewer.databinding.MainFragmentBinding
import com.geekbrains.lavsam.TMDBViewer.model.AppState
import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail
import com.geekbrains.lavsam.TMDBViewer.model.dto.MovieSearchDTO
import com.geekbrains.lavsam.TMDBViewer.model.dto.ResultsMovie
import com.geekbrains.lavsam.TMDBViewer.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val TMDB_API_KEY = BuildConfig.TMDB_API_KEY

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = MainFragmentAdapter()
    private var isDataSetLocal: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter.removeOnItemViewClickListener()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.setOnItemViewClickListener(object : OnItemViewClickListener {
            override fun onItemViewClick(movieDetail: MovieDetail) {
                val manager = activity?.supportFragmentManager
                if (manager != null) {
                    val bundle = Bundle()
                    bundle.putParcelable(DetailsFragment.BUNDLE_EXTRA, movieDetail)
                    manager.beginTransaction()
                        .replace(R.id.container, DetailsFragment.newInstance(bundle))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }
        })

        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener {
            changeMovieDataSet()
        }
        val observer = Observer<AppState> { a ->
            renderData(a)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getMovieFromLocalSourse()
        val qR = getMoviesTMDB()
    }

    private fun changeMovieDataSet() =
        if (isDataSetLocal) {
            viewModel.getMovieFromLocalSourse()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_world_movies)
        } else {
            viewModel.getMovieFromTMDBSource()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_local_movies)
        }.also { isDataSetLocal = !isDataSetLocal }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                binding.loadingLayout.hide()
                adapter.setMovieDetail(data.movieData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.show()
            }
            is AppState.Error -> {
                binding.loadingLayout.hide()
                binding.mainFragmentFAB.showSnackBarAction(
                    getString(R.string.Error),
                    getString(R.string.Reload)
                )
                {
                    if (isDataSetLocal) {
                        viewModel.getMovieFromLocalSourse()
                    } else {
                        viewModel.getMovieFromTMDBSource()
                    }
                }
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movieDetail: MovieDetail)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getMoviesTMDB(): ArrayList<ResultsMovie>? {
        var queryResult: ArrayList<ResultsMovie>? = null
        val query = "Race"
        val urlMovies = "https://api.themoviedb.org/3/search/movie?api_key=$TMDB_API_KEY&language=en-US&query=$query&page=1"
        try {
            val uriMovies = URL(urlMovies)
            val handler = Handler(Looper.myLooper()!!)
            Thread(Runnable {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uriMovies.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))

                    // преобразование ответа от сервера (JSON) в модель данных (WeatherDTO)
                    val movieSearchDTO: MovieSearchDTO =
                        Gson().fromJson(getLines(bufferedReader), MovieSearchDTO::class.java)
                    handler.post { queryResult = displayMovies(movieSearchDTO) }

                } catch (e: Exception) {
                    Log.e("Movis", "Fail connection", e)
                    e.printStackTrace()
                    //Обработка ошибки
                } finally {
                    urlConnection.disconnect()
                }
            }).start()
        } catch (e: MalformedURLException) {
            Log.e("Movies", "Fail URI", e)
            e.printStackTrace()
            //Обработка ошибки
        }
        return queryResult
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun displayMovies(movieSearchDTO: MovieSearchDTO): ArrayList<ResultsMovie>? {
        val page = movieSearchDTO.page
        return movieSearchDTO.results

    }
}