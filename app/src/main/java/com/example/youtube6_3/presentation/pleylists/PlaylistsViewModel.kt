package com.example.youtube6_3.presentation.pleylists

import androidx.lifecycle.LiveData
import com.example.youtube6_3.core.base.BaseViewModel
import com.example.youtube6_3.core.utils.Resource
import com.example.youtube6_3.data.model.PlaylistsModel
import com.example.youtube6_3.domain.repositoty.Repository


class PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {

    fun getPlaylists(): LiveData<Resource<PlaylistsModel>> {
        return repository.getPlaylists()
    }
}