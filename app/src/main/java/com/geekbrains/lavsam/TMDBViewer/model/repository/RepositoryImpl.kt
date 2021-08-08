package com.geekbrains.lavsam.TMDBViewer.model.repository

import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail
import com.geekbrains.lavsam.TMDBViewer.model.data.getMovies

class RepositoryImpl : Repository {

    override fun getMovieFromTMDB(): List<MovieDetail> = getMovies()

    override fun getMovieFromLocalStorage(): List<MovieDetail> = getMovies()
}