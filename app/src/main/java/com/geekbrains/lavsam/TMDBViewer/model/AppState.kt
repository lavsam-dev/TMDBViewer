package com.geekbrains.lavsam.TMDBViewer.model

import com.geekbrains.lavsam.TMDBViewer.model.data.MovieDetail

sealed class AppState {
    data class Success(val movieData: List<MovieDetail>) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
