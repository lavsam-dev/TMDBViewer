package com.geekbrains.lavsam.TMDBViewer.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieList(
    val movies: ArrayList<MovieDetail>
) : Parcelable
