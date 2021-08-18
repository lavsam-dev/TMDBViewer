package com.geekbrains.lavsam.TMDBViewer.model.repository

import com.geekbrains.lavsam.TMDBViewer.BuildConfig
import com.geekbrains.lavsam.TMDBViewer.model.data.MovieList
import com.geekbrains.lavsam.TMDBViewer.model.dto.MovieDetailDTO
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TMDB_API_KEY = BuildConfig.TMDB_API_KEY
private const val LANGUAGE = "en-US"
private const val MAIN_URL = "https://api.themoviedb.org/"

class RemoteDataSource {
    private val movieAPI = Retrofit.Builder()
        .baseUrl(MAIN_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .build()
        .create(MovieAPI::class.java)

    fun getMovieDetails(id: Int?, callback: Callback<MovieDetailDTO>) {
        movieAPI.getMovie(id, TMDB_API_KEY, LANGUAGE).enqueue(callback)
    }

    fun getMovieList(page: Int, callback: Callback<MovieList>) {
        movieAPI.getMovieList(TMDB_API_KEY, LANGUAGE, page).enqueue(callback)
    }
}