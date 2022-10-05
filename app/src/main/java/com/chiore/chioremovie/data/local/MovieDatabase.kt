package com.chiore.chioremovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chiore.chioremovie.data.model.movies.MovieDetailResponse

@Database(
    entities = [MovieDetailResponse::class],
    version = 1
)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

}