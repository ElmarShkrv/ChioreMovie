package com.chiore.chioremovie.repository

import com.chiore.chioremovie.data.local.MovieDao
import com.chiore.chioremovie.data.model.movies.*
import com.chiore.chioremovie.data.remote.MovieApi
import com.chiore.chioremovie.util.Resource
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao,
) {

    suspend fun upsert(movieDetailResponse: MovieDetailResponse) = movieDao.upsert(movieDetailResponse)

    suspend fun getRecommendationMovies(movieId: Int): Resource<MovieResponse> {
        return try {
            val response = movieApi.getRecommendationMovies(movieId)
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

    suspend fun getSimilarMovies(movieId: Int): Resource<MovieResponse> {
        return try {
            val response = movieApi.getSimilarMovies(movieId)
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

    suspend fun getCasts(movieId: Int): Resource<CastsResponse> {
        return try {
            val response = movieApi.getCasts(movieId)
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

    suspend fun getReview(movieId: Int): Resource<ReviewsResponse> {
        return try {
            val response = movieApi.getReview(movieId)
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