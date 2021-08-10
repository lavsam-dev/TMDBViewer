package com.geekbrains.lavsam.TMDBViewer.model.dto

data class MoviePopularDTO(
    val page: Int?,
    val results: ArrayList<ResultsMovie>?
)

data class ResultsMovie(
    val id: Int?,
    val name: String?
)
