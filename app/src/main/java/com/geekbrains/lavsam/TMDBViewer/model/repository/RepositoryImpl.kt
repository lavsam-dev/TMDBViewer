package com.geekbrains.lavsam.TMDBViewer.model.repository

import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail
import com.geekbrains.lavsam.TMDBViewer.model.data.getMovies

class RepositoryImpl : Repository {
    override fun getMovieFromTMDB(): List<MovieDetail> {
        return listOf(MovieDetail())
    }

    override fun getMovieFromLocalStorage(): List<MovieDetail> {
        return getMovies()
    }
}