package com.geekbrains.lavsam.TMDBViewer.model.repository

import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail

interface Repository {
    fun getMovieFromTMDB(): List<MovieDetail>
    fun getMovieFromLocalStorage(): List<MovieDetail>
}