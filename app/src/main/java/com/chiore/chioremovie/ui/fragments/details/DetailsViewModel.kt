package com.chiore.chioremovie.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.chioremovie.data.model.movies.MovieDetailResponse
import com.chiore.chioremovie.data.model.movies.Result
import com.chiore.chioremovie.data.model.movies.ReviewsResponse
import com.chiore.chioremovie.repository.DetailsRepository
import com.chiore.chioremovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DetailsRepository,
) : ViewModel() {

    private val _detailsReview = MutableLiveData<Resource<ReviewsResponse>>(Resource.Loading())
    val detailsReview: LiveData<Resource<ReviewsResponse>> = _detailsReview

    fun detailReview(movieId: Int) {
        viewModelScope.launch {
            val response = repository.getReview(movieId)
            response.data?.let {
                _detailsReview.value = Resource.Success(it)
            }
        }
    }

    private val _detailsMovie = MutableLiveData<Resource<MovieDetailResponse>>(Resource.Loading())
    val detailsMovie: LiveData<Resource<MovieDetailResponse>> = _detailsMovie

    fun movieDetails(movieId: Int) {
        viewModelScope.launch {
            val response = repository.movieDetails(movieId)
            response.data?.let {
                _detailsMovie.value = Resource.Success(it)
            }
        }
    }

    private val _detailsMovieVideos = MutableLiveData<Resource<List<Result>>>(Resource.Loading())
    val detailsMovieVideos: LiveData<Resource<List<Result>>> = _detailsMovieVideos

    fun movieVideo(movieId: Int) {
        viewModelScope.launch {
            val response = repository.movieVideos(movieId)
            response.data?.let {
                _detailsMovieVideos.value = Resource.Success(it.results)
            }
        }
    }

}