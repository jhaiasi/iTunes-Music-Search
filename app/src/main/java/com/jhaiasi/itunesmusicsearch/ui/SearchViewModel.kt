package com.jhaiasi.itunesmusicsearch.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhaiasi.itunesmusicsearch.data.MusicRepository
import com.jhaiasi.itunesmusicsearch.data.Track
import kotlinx.coroutines.launch

/**
 * The ViewModel for [SearchFragment]
 */
class SearchViewModel @ViewModelInject internal constructor(
    private val musicRepository: MusicRepository
) : ViewModel() {

    val searchResults: LiveData<List<Track>> = musicRepository.getSearchResults()
    val timestamp: LiveData<String> = musicRepository.getTimestamp()

    init {
        viewModelScope.launch {
            musicRepository.loadTracksFromDatabase()
            musicRepository.loadTimestampFromSharedPref()
        }
    }

    fun searchMusic(keywords: String) = musicRepository.searchMusic(keywords)
}