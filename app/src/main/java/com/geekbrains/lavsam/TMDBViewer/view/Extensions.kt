package com.geekbrains.lavsam.TMDBViewer.view

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBarAction(text: String, actionText: String, acttion: (View) -> Unit) {
    Snackbar.make(this, text, Snackbar.LENGTH_INDEFINITE)
        .setAction(actionText, acttion).show()
}

fun View.show(): View{
    if (visibility != View.VISIBLE){
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View{
    if (visibility != View.GONE){
        visibility = View.GONE
    }
    return this
}