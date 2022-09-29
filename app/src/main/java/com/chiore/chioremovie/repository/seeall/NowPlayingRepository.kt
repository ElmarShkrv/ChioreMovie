package com.chiore.chioremovie.repository.seeall

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.chiore.chioremovie.data.remote.MovieApi
import com.chiore.chioremovie.paging.NowPlayingPagingSource
import javax.inject.Inject

class NowPlayingRepository @Inject constructor(
    private val movieApi: MovieApi
) {
    fun getAllNowPlayingMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {NowPlayingPagingSource(movieApi)}
        ).liveData
}