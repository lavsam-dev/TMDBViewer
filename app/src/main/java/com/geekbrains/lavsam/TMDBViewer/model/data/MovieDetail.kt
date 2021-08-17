package com.geekbrains.lavsam.TMDBViewer.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetail(
    val id: Int? = 0,
    val original_title: String? = "",
    val title: String? = "",
    val release_date: String? = "",
    val poster_path: String? = "",
    val backdrop_path: String? = "",
    val overview: String? = "",
    val vote_average: Double? = 0.0,
    val runtime: Int? = 0
) : Parcelable
