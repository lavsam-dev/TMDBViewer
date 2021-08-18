package com.geekbrains.lavsam.TMDBViewer.model.repository

import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail
import com.geekbrains.lavsam.TMDBViewer.model.data.MovieList
import retrofit2.Callback

interface Repository {
    fun getMovieFromTMDB(): MovieDetail
    fun getMoviesListFromTMDB(page: Int, callback: Callback<MovieList>)
}
