package com.chiore.chioremovie.repository

import com.chiore.chioremovie.data.model.movies.MovieDetailResponse
import com.chiore.chioremovie.data.model.movies.MovieTrailerResponse
import com.chiore.chioremovie.data.remote.MovieApi
import com.chiore.chioremovie.util.Resource
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val movieApi: MovieApi,
) {
    suspend fun movieDetails(movieId: Int): Resource<MovieDetailResponse> {
        return try {
            val response = movieApi.getMovieDetails(movieId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                } ?: Resource.Error("Response is null")
            } else {
                Resource.Error("Response is not successful")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    suspend fun movieVideos(movieId: Int): Resource<MovieTrailerResponse> {
        return try {
            val response = movieApi.getMovieVideos(movieId)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                } ?: Resource.Error("Response is null")
            } else {
                Resource.Error("Response is not successful")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}