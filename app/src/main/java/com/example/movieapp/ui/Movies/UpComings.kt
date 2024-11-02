package com.example.movieapp.ui.Movies

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.Api.RetrofitClient
import com.example.movieapp.GetUpComing.Repository.MovieRepository
import com.example.movieapp.GetUpComing.viewmodel.MoviesViewModel
import com.example.movieapp.GetUpComing.viewmodelfactory.UpComingViewModelFactory
import com.example.movieapp.databinding.ActivityUpComingsBinding
import com.example.movieapp.ui.Movies.trendings.Adapter.trendingAdapter
import com.example.movieapp.ui.Movies.trendings.Model.trendingModel
import com.example.movieapp.ui.Movies.upcomings.Adapter.MovieAdapter
import com.example.movieapp.ui.Movies.upcomings.Model.UpComingMoviesObject

class UpComings : AppCompatActivity() {
    lateinit var viewModel: MoviesViewModel
    lateinit var binding: ActivityUpComingsBinding
    lateinit var adapter: MovieAdapter
    lateinit var trendingAdapter: trendingAdapter

    // Define a request code for handling the permission result
    companion object {
        private const val REQUEST_CODE_POST_NOTIFICATIONS = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpComingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = MovieRepository(RetrofitClient.api)
        val viewModelFactory = UpComingViewModelFactory(repo)

        viewModel = ViewModelProvider(this, viewModelFactory)[MoviesViewModel::class.java]
        requestNotificationPermission()
        //Up-Comings
        UpComingMovies()

        //Trending
        trendingMovies()

    }

    fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Request the permission
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_CODE_POST_NOTIFICATIONS
                )
            }
        }
    }

    private fun trendingMovies() {

        //Response
        viewModel.trendingMovieLiveData.observe(this, Observer { response ->
            response?.let {
                val trendingMovie: MutableList<trendingModel> = mutableListOf()
                it.movies.take(7).forEach { trendingMovieData ->
                    val obj = trendingModel(
                        trendingMovieData.title,
                        trendingMovieData.image,
                        trendingMovieData.imdbRating
                    )
                    trendingMovie.add(obj)
                }
                binding.trendingRecView.layoutManager =
                    LinearLayoutManager(this@UpComings, LinearLayoutManager.HORIZONTAL, false)
                trendingAdapter = trendingAdapter(this@UpComings, trendingMovie)
                binding.trendingRecView.adapter = trendingAdapter
                binding.trendingRecView.setItemViewCacheSize(20)   // Increase cache size if needed
                binding.trendingRecView.setHasFixedSize(true)

            }

        })

        //Error
        viewModel.error.observe(this, Observer { error ->
            error?.let {
                Log.d("error", it.toString())
            }
        })

        //Fetch Data
        viewModel.getTrendingMoviesData()
    }

    fun UpComingMovies() {
        //Response
        viewModel.upComingMovieLiveData.observe(this, Observer { response ->
            response?.let {
                val linkList: MutableList<UpComingMoviesObject> = mutableListOf()
                it.movies.take(7).forEach { moviesName ->
                    // Check if list is not empty and the first element's image is not null or empty
                    val firstMovie = moviesName.list.firstOrNull()
                    if (firstMovie != null && !firstMovie.image.isNullOrEmpty()) {
                        val obj = UpComingMoviesObject(firstMovie.title, firstMovie.image)
                        linkList.add(obj)
                    }
                }

                binding.upComingMovies.layoutManager =
                    LinearLayoutManager(this@UpComings, LinearLayoutManager.HORIZONTAL, false)
                adapter = MovieAdapter(this@UpComings, linkList)
                binding.upComingMovies.adapter = adapter
                binding.upComingMovies.setItemViewCacheSize(20)   // Increase cache size if needed
                binding.upComingMovies.setHasFixedSize(true)

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