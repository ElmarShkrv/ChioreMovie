package com.chiore.chioremovie.ui.fragments.seeallfragments.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chiore.chioremovie.repository.seeall.PopularRepository
import com.chiore.chioremovie.repository.seeall.UpcomingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val repository: UpcomingRepository
): ViewModel() {

    val allUpcomingMovies = repository.getAllUpcomingMovies().cachedIn(viewModelScope)

}