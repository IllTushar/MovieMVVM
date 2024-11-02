package com.example.movieapp.Api

import com.example.movieapp.GetUpComing.Model.Movies.UpComingMoviesResponse
import com.example.movieapp.GetUpComing.Model.Trending.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiServices {

    //Up-Coming Movies
    @Headers(
        "x-apihub-key: bV0ZGg3MvUzLKfRdJKpJ208oIZD4LsIMr0c7RrqnHM9XWhx4rI",
        "x-apihub-host: Movies-Verse.allthingsdev.co",
        "x-apihub-endpoint: 4f700f4a-4bd2-4604-8d5b-7b5e4c976c65"
    )
    @GET("api/movies/upcoming-movies")
    suspend fun getUpComingMovies(): Response<UpComingMoviesResponse>

    //Trending Movies
    @Headers(
        "x-apihub-key: bV0ZGg3MvUzLKfRdJKpJ208oIZD4LsIMr0c7RrqnHM9XWhx4rI",
        "x-apihub-host: Movies-Verse.allthingsdev.co",
        "x-apihub-endpoint: 611cdfda-546d-4cc9-91ab-bfdac3194613"
    )
    @GET("api/movies/most-popular-movies")
    suspend fun getTrendingMovies(): Response<TrendingResponse>
}