package com.example.movieapp.api

import com.example.movieapp.MovieResponse
import com.example.movieapp.ResultsMovieItem
import com.example.movieapp.ResultsTvItem
import com.example.movieapp.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{movieId}")
    fun getMovie(
        @Path("movieId") movieId: String,
        @Query("api_key") api_key: String
    ): Call<ResultsMovieItem>

    @GET("tv/{tvId}")
    fun getTv(
        @Path("tvId") tvId: String,
        @Query("api_key") api_key: String
    ): Call<ResultsTvItem>

    @GET("discover/movie")
    fun getListMovie(
        @Query("api_key") api_key: String
    ): Call<MovieResponse>

    @GET("discover/tv")
    fun getListTv(
        @Query("api_key") api_key: String
    ): Call<TvResponse>
}