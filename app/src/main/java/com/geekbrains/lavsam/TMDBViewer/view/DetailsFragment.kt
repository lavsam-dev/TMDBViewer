package com.geekbrains.lavsam.TMDBViewer.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrains.lavsam.TMDBViewer.databinding.DetailFragmentBinding
import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<MovieDetail>(BUNDLE_EXTRA)?.let { populateData(movieDetailData = it) }
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

    companion object {
        const val BUNDLE_EXTRA = "movieDetail"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}