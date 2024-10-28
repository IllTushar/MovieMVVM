package com.example.movieapp.GetUpComing.ui.Movies

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.Api.RetrofitClient
import com.example.movieapp.GetUpComing.Repository.UpComingRepository
import com.example.movieapp.GetUpComing.viewmodel.UpComingViewModel
import com.example.movieapp.GetUpComing.viewmodelfactory.UpComingViewModelFactory
import com.example.movieapp.databinding.ActivityUpComingsBinding

class UpComings : AppCompatActivity() {
    lateinit var viewModel: UpComingViewModel
    lateinit var binding: ActivityUpComingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpComingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = UpComingRepository(RetrofitClient.api)
        val viewModelFactory = UpComingViewModelFactory(repo)

        viewModel = ViewModelProvider(this, viewModelFactory)[UpComingViewModel::class.java]

        //Movies
        UpComingMovies()

    }

    fun UpComingMovies() {
        //Response
        viewModel.upComingMovieLiveData.observe(this, Observer { response ->
            response?.let {
                for (moviesName in it.movies) {
                    Log.d("Title", "${moviesName.list[0].title.toString()}")
                    Log.d("Link", "${moviesName.list[0].link.toString()}")
                }
            }
        })

        //Error
        viewModel.errorMessage.observe(this, Observer { error ->
            error?.let {
                Log.d("Error", "${it.toString()}")
            }
        })

        //Fetch Data
        viewModel.getUpComingMovieData()

    }
}