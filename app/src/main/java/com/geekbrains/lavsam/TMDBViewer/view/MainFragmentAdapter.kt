package com.geekbrains.lavsam.TMDBViewer.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.lavsam.TMDBViewer.databinding.MainRecyclerItemBinding
import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail

class MainFragmentAdapter :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var movieDetailData: List<MovieDetail> = listOf()
    private var onItemViewClickListener: MainFragment.OnItemViewClickListener? = null

    fun setOnItemViewClickListener(onItemViewClickListener: MainFragment.OnItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener
    }

    fun removeOnItemViewClickListener() {
        onItemViewClickListener = null
    }

    fun setMovieDetail(data: List<MovieDetail>) {
        movieDetailData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = MainRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movieDetailData[position])
    }

    override fun getItemCount(): Int {
        return movieDetailData.size
    }

    inner class MainViewHolder(val binding: MainRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieDetail: MovieDetail) {
            with(binding) {
                mainFragmentRecyclerItemTextView.text = movieDetail.original_title
                root.setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(movieDetail)
                }
            }
        }
    }
}