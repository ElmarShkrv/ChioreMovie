package com.chiore.chioremovie.ui.fragments.seeallfragments.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.chiore.chioremovie.data.model.movies.ReviewsResult
import com.chiore.chioremovie.repository.seeall.ReviewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val repository: ReviewRepository,
) : ViewModel() {

    private val _seeAllReviews = MutableLiveData<LiveData<PagingData<ReviewsResult>>>()
    val seeAllReviews: MutableLiveData<LiveData<PagingData<ReviewsResult>>> = _seeAllReviews

    fun allReview(movieId: Int) {
        val response = repository.getAllReview(movieId).cachedIn(viewModelScope)
        _seeAllReviews.value = response
    }

}