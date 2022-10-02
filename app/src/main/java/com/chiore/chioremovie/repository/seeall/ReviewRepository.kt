package com.chiore.chioremovie.repository.seeall

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.chiore.chioremovie.data.remote.MovieApi
import com.chiore.chioremovie.paging.NowPlayingPagingSource
import com.chiore.chioremovie.paging.ReviewPagingSource
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val movieApi: MovieApi
) {
    fun getAllReview(movieId: Int) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {ReviewPagingSource(movieApi, movieId)}
        ).liveData
}