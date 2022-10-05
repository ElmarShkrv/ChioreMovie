package com.chiore.chioremovie.di

import android.content.Context
import android.provider.DocumentsContract.Root
import androidx.room.Room
import com.chiore.chioremovie.data.local.MovieDatabase
import com.chiore.chioremovie.data.remote.MovieApi
import com.chiore.chioremovie.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, MovieDatabase::class.java, "MovieDB"
    ).build()

    @Provides
    @Singleton
    fun injectDao(database: MovieDatabase) = database.movieDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

}