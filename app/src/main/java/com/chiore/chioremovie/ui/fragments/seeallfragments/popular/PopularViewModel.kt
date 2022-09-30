package com.chiore.chioremovie.ui.fragments.seeallfragments.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chiore.chioremovie.repository.seeall.PopularRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: PopularRepository
): ViewModel() {

    val allPopularMovies = repository.getAllPopularMovies().cachedIn(viewModelScope)

}