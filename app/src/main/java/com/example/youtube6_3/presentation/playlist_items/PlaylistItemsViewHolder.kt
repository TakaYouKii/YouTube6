package com.example.youtube6_3.presentation.playlist_items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtube6_3.core.base.BaseViewModel
import com.example.youtube6_3.data.model.PlaylistsModel
import com.example.youtube6_3.domain.repositoty.Repository

class PlayListItemViewModel(private val repository: Repository): BaseViewModel() {
    private val _playlists = MutableLiveData<PlaylistsModel>()
    val playlistsItem: LiveData<PlaylistsModel> get() = _playlists
    fun getPlaylists(id:String) =doOperation(
        operation = { repository.getPlaylistItems(id)},
        success = { _playlists.postValue(it) })
}