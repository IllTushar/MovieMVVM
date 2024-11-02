package com.example.movieapp.GetUpComing.Repository

import com.example.movieapp.Api.ApiServices
import com.example.movieapp.GetUpComing.Model.Movies.UpComingMoviesResponse
import com.example.movieapp.GetUpComing.Model.Trending.TrendingResponse
import retrofit2.Response

class MovieRepository(private val apiServices: ApiServices) {
    suspend fun getUpComingMovie(): Response<UpComingMoviesResponse> {
        return apiServices.getUpComingMovies()
    }

    suspend fun getTrendingMovie(): Response<TrendingResponse> {
        return apiServices.getTrendingMovies()
    }
}