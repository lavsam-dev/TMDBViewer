package com.geekbrains.lavsam.TMDBViewer.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetail(
    val movie: Movie = getDefaultMovie(),
    val overview: String = "Overview",
    val genre: String = "Genre",
    val director: String = "Director",
    val runtime: String = "Runtime"
) : Parcelable

fun getDefaultMovie() = Movie(2527, "Emmerdale", "", 1972, "39%")
