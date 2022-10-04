package com.chiore.chioremovie.ui.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.chioremovie.data.model.movies.ResultDto
import com.chiore.chioremovie.repository.SearchRepository
import com.chiore.chioremovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository,
) : ViewModel() {

    private val _searchMovie = MutableLiveData<Resource<List<ResultDto>>>(Resource.Loading())
    val searchMovie: LiveData<Resource<List<ResultDto>>> = _searchMovie

    fun searchMovie(qery: String) {
        viewModelScope.launch {
            val response = repository.searchMovie(qery)
            response.data?.let {
                _searchMovie.value = Resource.Success(it.results)
            }
        }
    }

}