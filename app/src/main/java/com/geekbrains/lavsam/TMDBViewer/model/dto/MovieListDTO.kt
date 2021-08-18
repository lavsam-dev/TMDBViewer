package com.geekbrains.lavsam.TMDBViewer.model.dto

import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail

data class MovieListDTO(
    val page: Int,
    val list: ArrayList<MovieDetail>
)
