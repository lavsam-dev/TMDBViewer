package com.geekbrains.lavsam.TMDBViewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.lavsam.TMDBViewer.model.AppState
import com.geekbrains.lavsam.TMDBViewer.model.repository.Repository
import com.geekbrains.lavsam.TMDBViewer.model.repository.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(private val repository: Repository = RepositoryImpl()) : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> {
        return liveDataToObserve
    }

    fun getMovieFromLocalSourse() = getDataFromSource(isLocal = true)

    fun getMovieFromTMDBSource() = getDataFromSource(isLocal = false)

    private fun getDataFromSource(isLocal: Boolean) {
        liveDataToObserve.value = AppState.Loading
        if (isLocal) {
            Thread {
                liveDataToObserve.postValue(
                    AppState.Success(repository.getMovieFromLocalStorage())
                )
            }.start()
        } else {
            Thread {
                sleep(2000)
                liveDataToObserve.postValue(
                    AppState.Success(repository.getMovieFromTMDB())
                )
            }.start()
        }
    }
}