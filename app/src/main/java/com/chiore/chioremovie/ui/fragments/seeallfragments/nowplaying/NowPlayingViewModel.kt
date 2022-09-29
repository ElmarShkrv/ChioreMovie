package com.chiore.chioremovie.ui.fragments.seeallfragments.nowplaying

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.chiore.chioremovie.repository.seeall.NowPlayingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(
    private val repository: NowPlayingRepository
): ViewModel() {

    val allNowPlayingMovies = repository.getAllNowPlayingMovies().cachedIn(viewModelScope)

}