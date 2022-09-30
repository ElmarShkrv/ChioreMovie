package com.chiore.chioremovie.repository.seeall

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.chiore.chioremovie.data.remote.MovieApi
import com.chiore.chioremovie.paging.PopularPagingSource
import javax.inject.Inject

class PopularRepository @Inject constructor(
    private val movieApi: MovieApi,
) {
    fun getAllPopularMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PopularPagingSource(movieApi) }
        ).liveData
}