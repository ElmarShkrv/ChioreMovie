package com.chiore.chioremovie.ui.fragments.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chiore.chioremovie.data.model.movies.MovieDetailResponse
import com.chiore.chioremovie.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,
) : ViewModel() {

    fun saveMovie(movieDetailResponse: MovieDetailResponse) = viewModelScope.launch {
        repository.upsert(movieDetailResponse)
    }

    fun getSavedMovies() = repository.getSavedMovies()

    fun deleteMovie(movieDetailResponse: MovieDetailResponse) = viewModelScope.launch {
        repository.deleteMovie(movieDetailResponse)
    }

}