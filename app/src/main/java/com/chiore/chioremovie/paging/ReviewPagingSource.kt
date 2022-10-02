package com.chiore.chioremovie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chiore.chioremovie.data.model.movies.ResultDto
import com.chiore.chioremovie.data.model.movies.ReviewsResult
import com.chiore.chioremovie.data.remote.MovieApi
import com.chiore.chioremovie.util.Constants.Companion.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class ReviewPagingSource(
    private val movieApi: MovieApi,
    private val movieId: Int,
) : PagingSource<Int, ReviewsResult>() {

    override fun getRefreshKey(state: PagingState<Int, ReviewsResult>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewsResult> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = movieApi.getAllReview(movieId, position)
            val movies = response.body()!!.results

            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}