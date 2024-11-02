package com.example.movieapp.GetUpComing.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.GetUpComing.Repository.MovieRepository
import com.example.movieapp.GetUpComing.viewmodel.MoviesViewModel

class UpComingViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(repo) as T
        }
        throw IllegalArgumentException("UnKnown Exception")
    }
}