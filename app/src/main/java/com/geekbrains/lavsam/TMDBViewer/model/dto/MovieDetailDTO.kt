package com.geekbrains.lavsam.TMDBViewer.model.dto

data class MovieDetailDTO(
    val adult: Boolean?,
    val budget: Int?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val runtime: Int?,
    val release_date: String?,
    val vote_average: Double?,
    val vote_count: Int?
)
