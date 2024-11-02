package com.example.movieapp.GetUpComing.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.GetUpComing.Model.Movies.UpComingMoviesResponse
import com.example.movieapp.GetUpComing.Model.Trending.TrendingResponse
import com.example.movieapp.GetUpComing.Repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MoviesViewModel(private val repo: MovieRepository) : ViewModel() {
    var upComingMovies: MutableLiveData<UpComingMoviesResponse> = MutableLiveData()
    val upComingMovieLiveData: LiveData<UpComingMoviesResponse>
        get() = upComingMovies

    var error: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String>
        get() = error

    var trendingMovie: MutableLiveData<TrendingResponse> = MutableLiveData()
    val trendingMovieLiveData: LiveData<TrendingResponse>
        get() = trendingMovie

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

    fun getTrendingMoviesData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getTrendingMovie()
            if (result.isSuccessful && result.body() != null) {
                if (result.code() == 200 ) {
                    trendingMovie.postValue(result.body())
                } else {
                    error.postValue(result.code().toString())
                }
            } else {
                error.postValue(result.message().toString())
            }
        }
    }
}