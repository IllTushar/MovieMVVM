package com.example.movieapp.GetUpComing.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.GetUpComing.Repository.UpComingRepository
import com.example.movieapp.GetUpComing.viewmodel.UpComingViewModel

class UpComingViewModelFactory(private val repo: UpComingRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpComingViewModel::class.java)) {
            return UpComingViewModel(repo) as T
        }
        throw IllegalArgumentException("UnKnown Exception")
    }
}