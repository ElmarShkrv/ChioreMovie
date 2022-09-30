package com.chiore.chioremovie.repository.seeall

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.chiore.chioremovie.data.remote.MovieApi
import com.chiore.chioremovie.paging.PopularPagingSource
import com.chiore.chioremovie.paging.TopRatedPagingSource
import com.chiore.chioremovie.paging.UpcomingPagingSource
import javax.inject.Inject

class TopRatedRepository @Inject constructor(
    private val movieApi: MovieApi,
) {
    fun getAllTopRatedMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { TopRatedPagingSource(movieApi) }
        ).liveData
}