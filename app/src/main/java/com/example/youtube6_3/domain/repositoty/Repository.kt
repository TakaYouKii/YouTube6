package com.example.youtube6_3.domain.repositoty

import com.example.youtube6_3.core.network.RemoteDataSource
import com.example.youtube6_3.data.model.PlaylistsModel

class Repository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getPlaylists(): Result<PlaylistsModel> {
        return remoteDataSource.getPlaylists()
    }

    suspend fun getPlaylistItems(playlistId: String): Result<PlaylistsModel> {
        return remoteDataSource.getPlaylistItems(playlistId)
    }
}