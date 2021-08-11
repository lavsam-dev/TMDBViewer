package com.geekbrains.lavsam.TMDBViewer.model.dto

data class MovieSearchDTO(
    val page: Int?,
    val total_pages: Int?,
    val total_results: Int?,
    val results: ArrayList<ResultsMovie>?
)

data class ResultsMovie(
    val id: Int?,
    val original_title: String?
)
