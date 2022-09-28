package com.chiore.chioremovie.ui.fragments.homefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.chioremovie.data.model.movies.ResultDto
import com.chiore.chioremovie.repository.HomeRepository
import com.chiore.chioremovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    private val _nowPlayingMovie = MutableLiveData<Resource<List<ResultDto>>>(Resource.Loading())
    val nowPlayingMovie: LiveData<Resource<List<ResultDto>>> = _nowPlayingMovie

    fun nowPlayingMovies() {
        viewModelScope.launch {
            val response = repository.nowPlayinMovie()
            response.data?.let {
                _nowPlayingMovie.value = Resource.Success(it.results)
            }
        }
    }

    private val _popularMovie = MutableLiveData<Resource<List<ResultDto>>>(Resource.Loading())
    val popularMovie: LiveData<Resource<List<ResultDto>>> = _popularMovie

    fun popularMovies() {
        viewModelScope.launch {
            val response = repository.popularMovies()
            response.data?.let {
                _popularMovie.value = Resource.Success(it.results)
            }
        }
    }

    private val _upcomingMovie = MutableLiveData<Resource<List<ResultDto>>>(Resource.Loading())
    val upcomingMovie: LiveData<Resource<List<ResultDto>>> = _upcomingMovie

    fun upcomingMovies() {
        viewModelScope.launch {
            val response = repository.upcomingMovies()
            response.data?.let {
                _upcomingMovie.value = Resource.Success(it.results)
            }
        }
    }

    private val _topRatedMovie = MutableLiveData<Resource<List<ResultDto>>>(Resource.Loading())
    val topRatedMovie: LiveData<Resource<List<ResultDto>>> = _topRatedMovie

    fun topRatedMovies() {
        viewModelScope.launch {
            val response = repository.topRatedMovies()
            response.data?.let {
                _topRatedMovie.value = Resource.Success(it.results)
            }
        }
    }

}