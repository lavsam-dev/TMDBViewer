package com.geekbrains.lavsam.TMDBViewer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.lavsam.TMDBViewer.R
import com.geekbrains.lavsam.TMDBViewer.databinding.MainFragmentBinding
import com.geekbrains.lavsam.TMDBViewer.model.AppState
import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail
import com.geekbrains.lavsam.TMDBViewer.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

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
    }

    private fun changeMovieDataSet() =
        if (isDataSetLocal) {
            viewModel.getMovieFromLocalSourse()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_world_movies)
        } else {
            viewModel.getMovieFromTMDBSource()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_local_movies)
        }.also { isDataSetLocal = !isDataSetLocal }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                val movieDetailData = data.movieData
                binding.loadingLayout.visibility = View.GONE
                adapter.setMovieDetail(movieDetailData)
                Snackbar.make(binding.mainFragmentFAB, getString(R.string.Load), Snackbar.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainFragmentFAB, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reload") {
                        if (isDataSetLocal) viewModel.getMovieFromLocalSourse()
                        else viewModel.getMovieFromTMDBSource()
                    }
                    .show()
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(movieDetail: MovieDetail)
    }
}