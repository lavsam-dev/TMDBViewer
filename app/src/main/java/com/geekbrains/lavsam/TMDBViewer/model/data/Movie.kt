package com.geekbrains.lavsam.TMDBViewer.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val name: String,
    val posters: String,
    val year: Int,
    val rated: String,
) : Parcelable
