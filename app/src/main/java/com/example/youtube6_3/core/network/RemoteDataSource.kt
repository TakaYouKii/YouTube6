package com.example.youtube6_3.core.network

import com.example.youtube6_3.BuildConfig
import com.example.youtube6_3.core.base.BaseDataSource
import com.example.youtube6_3.data.model.PlaylistsModel
import com.example.youtube6_3.utils.Constants

class RemoteDataSource(private val apiService: ApiService): BaseDataSource()  {
    suspend fun getPlaylists(): Result<PlaylistsModel> {
        return getResult {
            apiService.getPlayLists(
                part = Constants.PART,
                channelId = Constants.CHANNEL_ID,
                apiKey = BuildConfig.API_KEY,
                maxResult = 22,
            )
        }
    }

    suspend fun getPlaylistItems(playlistId: String): Result<PlaylistsModel>{
        return getResult {
            apiService.getPlaylistItem(
                part = Constants.PART,
                apiKey = BuildConfig.API_KEY,
                playlistId = playlistId,
                maxResult = 22,
            )
        }
    }
}