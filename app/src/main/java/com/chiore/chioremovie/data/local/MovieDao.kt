package com.chiore.chioremovie.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chiore.chioremovie.data.model.movies.MovieDetailResponse

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movieDetailResponse: MovieDetailResponse): Long

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieDetailResponse>>

    @Delete
    suspend fun deleteMovies(movieDetailResponse: MovieDetailResponse)

}