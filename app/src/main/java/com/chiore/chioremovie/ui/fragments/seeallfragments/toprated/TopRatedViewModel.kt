package com.chiore.chioremovie.ui.fragments.seeallfragments.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chiore.chioremovie.repository.seeall.PopularRepository
import com.chiore.chioremovie.repository.seeall.TopRatedRepository
import com.chiore.chioremovie.repository.seeall.UpcomingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(
    private val repository: TopRatedRepository
): ViewModel() {

    val allTopRatedMovies = repository.getAllTopRatedMovies().cachedIn(viewModelScope)

}