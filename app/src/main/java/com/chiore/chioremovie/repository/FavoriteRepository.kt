package com.chiore.chioremovie.repository

import com.chiore.chioremovie.data.local.MovieDao
import com.chiore.chioremovie.data.model.movies.MovieDetailResponse
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val movieDao: MovieDao
) {
    suspend fun upsert(movieDetailResponse: MovieDetailResponse) = movieDao.upsert(movieDetailResponse)

    fun getSavedMovies() = movieDao.getAllMovies()

    suspend fun deleteMovie(movieDetailResponse: MovieDetailResponse) = movieDao.deleteMovies(movieDetailResponse)
}