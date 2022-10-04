package com.chiore.chioremovie.repository

import com.chiore.chioremovie.data.model.movies.MovieResponse
import com.chiore.chioremovie.data.remote.MovieApi
import com.chiore.chioremovie.util.Resource
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val movieApi: MovieApi,
) {
    suspend fun searchMovie(query: String): Resource<MovieResponse> {
        return try {
            val response = movieApi.getSearchMovies(query)
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error("Response is null")
            } else {
                Resource.Error("Response is not successful")
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}