package com.geekbrains.lavsam.TMDBViewer.model.repository

import com.geekbrains.lavsam.TMDBViewer.model.data.MovieList
import com.geekbrains.lavsam.TMDBViewer.model.dto.MovieDetailDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {
    @GET("3/movie/{id}")
    fun getMovie (
        @Path("id") id: Int?,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ) : Call<MovieDetailDTO>

    @GET("3/movie/popular")
    fun getMovieList (
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ) : Call<MovieList>
}
