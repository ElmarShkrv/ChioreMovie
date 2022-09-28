package com.chiore.chioremovie.data.remote

import com.chiore.chioremovie.data.model.movies.MovieResponse
import com.chiore.chioremovie.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

    @GET("movie/top_rated?api_key=$API_KEY")
    suspend fun getTopRatedMovies(): Response<MovieResponse>

}