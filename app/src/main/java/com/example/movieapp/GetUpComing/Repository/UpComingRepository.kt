package com.example.movieapp.GetUpComing.Repository

import com.example.movieapp.Api.ApiServices
import com.example.movieapp.GetUpComing.Model.Movies.UpComingMoviesResponse
import retrofit2.Response

class UpComingRepository(private val apiServices: ApiServices) {
    suspend fun getUpComingMovie(): Response<UpComingMoviesResponse> {
        return apiServices.getUpComingMovies()
    }
}