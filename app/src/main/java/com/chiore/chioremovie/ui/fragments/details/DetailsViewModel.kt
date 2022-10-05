package com.chiore.chioremovie.ui.fragments.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.chioremovie.data.model.movies.*
import com.chiore.chioremovie.repository.DetailsRepository
import com.chiore.chioremovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DetailsRepository,
) : ViewModel() {

    fun saveMovie(movieDetailResponse: MovieDetailResponse) = viewModelScope.launch {
        repository.upsert(movieDetailResponse)
    }

    private val _detailsRecommendation = MutableLiveData<Resource<List<ResultDto>>>(Resource.Loading())
    val detailsRecommendation: LiveData<Resource<List<ResultDto>>> = _detailsRecommendation

    fun detailsRecommendationMovies(movieId: Int) {
        viewModelScope.launch {
            val response = repository.getRecommendationMovies(movieId)
            response.data?.let {
                _detailsRecommendation.value = Resource.Success(it.results)
            }
        }
    }

    private val _detailsSimilar = MutableLiveData<Resource<List<ResultDto>>>(Resource.Loading())
    val detailsSimilar: LiveData<Resource<List<ResultDto>>> = _detailsSimilar

    fun detailSimilarMovie(movieId: Int) {
        viewModelScope.launch {
            val response = repository.getSimilarMovies(movieId)
            response.data?.let {
                _detailsSimilar.value = Resource.Success(it.results)
            }
        }
    }

    private val _detailsCasts = MutableLiveData<Resource<CastsResponse>>(Resource.Loading())
    val detailsCasts: LiveData<Resource<CastsResponse>> = _detailsCasts

    fun detailCasts(movieId: Int) {
        viewModelScope.launch {
            val response = repository.getCasts(movieId)
            response.data?.let {
                _detailsCasts.value = Resource.Success(it)
            }
        }
    }

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