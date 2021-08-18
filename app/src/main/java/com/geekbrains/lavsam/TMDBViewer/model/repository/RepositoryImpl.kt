package com.geekbrains.lavsam.TMDBViewer.model.repository

import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail
import com.geekbrains.lavsam.TMDBViewer.model.data.MovieList
import retrofit2.Callback

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {

    override fun getMovieFromTMDB() = MovieDetail()

    override fun getMoviesListFromTMDB(page: Int, callback: Callback<MovieList>) {
        remoteDataSource.getMovieList(page, callback)
    }
}