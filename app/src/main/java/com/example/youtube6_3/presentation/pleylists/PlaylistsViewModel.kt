package com.example.youtube6_3.presentation.pleylists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube6_3.core.base.BaseViewModel
import com.example.youtube6_3.data.model.PlaylistsModel
import com.example.youtube6_3.domain.repositoty.Repository




class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {
    private val _playlists = MutableLiveData<PlaylistsModel>()
    val playlists: LiveData<PlaylistsModel> get() = _playlists

    fun getPlaylists() {
        doOperation(
            operation = { repository.getPlaylists()},
            success = { _playlists.postValue(it) })
    }
}