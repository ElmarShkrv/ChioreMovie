package com.chiore.chioremovie.repository

import com.chiore.chioremovie.data.model.movies.MovieResponse
import com.chiore.chioremovie.data.remote.MovieApi
import com.chiore.chioremovie.util.Resource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val movieApi: MovieApi,
) {
    suspend fun nowPlayinMovie(): Resource<MovieResponse> {
        return try {
            val response = movieApi.getNowPlayingMovies()
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

    suspend fun popularMovies(): Resource<MovieResponse> {
        return try {
            val response = movieApi.getPopularMovies()
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

    suspend fun upcomingMovies(): Resource<MovieResponse> {
        return try {
            val response = movieApi.getUpcomingMovies()
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

    suspend fun topRatedMovies(): Resource<MovieResponse> {
        return try {
            val response = movieApi.getTopRatedMovies()
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