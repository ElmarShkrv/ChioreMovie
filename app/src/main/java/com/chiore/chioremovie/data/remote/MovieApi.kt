package com.chiore.chioremovie.data.remote

import com.chiore.chioremovie.data.model.movies.MovieDetailResponse
import com.chiore.chioremovie.data.model.movies.MovieResponse
import com.chiore.chioremovie.data.model.movies.MovieTrailerResponse
import com.chiore.chioremovie.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

    @GET("movie/top_rated?api_key=$API_KEY")
    suspend fun getTopRatedMovies(): Response<MovieResponse>



    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getAllNowPlayingMovies(
        @Query("page") position: Int,
    ): Response<MovieResponse>

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getAllPopularMovies(
        @Query("page") position: Int,
    ): Response<MovieResponse>

    @GET("movie/upcoming?api_key=$API_KEY")
    suspend fun getAllUpcomingMovies(
        @Query("page") position: Int,
    ): Response<MovieResponse>

    @GET("movie/top_rated?api_key=$API_KEY")
    suspend fun getAllTopRatedMovies(
        @Query("page") position: Int,
    ): Response<MovieResponse>



    @GET("movie/{movie_id}?api_key=$API_KEY")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): Response<MovieDetailResponse>

    @GET("movie/{movie_id}/videos?api_key=$API_KEY")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int
    ): Response<MovieTrailerResponse>

}