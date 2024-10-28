package com.example.movieapp.GetUpComing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.GetUpComing.Model.Movies.UpComingMoviesResponse
import com.example.movieapp.GetUpComing.Repository.UpComingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UpComingViewModel(private val repo: UpComingRepository) : ViewModel() {
    var upComingMovies: MutableLiveData<UpComingMoviesResponse> = MutableLiveData()
    val upComingMovieLiveData: LiveData<UpComingMoviesResponse>
        get() = upComingMovies

    var error: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String>
        get() = error

    fun getUpComingMovieData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getUpComingMovie()
            if (result.isSuccessful && result.body() != null) {
                if (result.code() == 200) {
                    upComingMovies.postValue(result.body())
                } else {
                    error.postValue(result.code().toString())
                }
            }
        }
    }
}