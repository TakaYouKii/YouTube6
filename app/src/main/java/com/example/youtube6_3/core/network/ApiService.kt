package com.example.youtube6_3.core.network

import com.example.youtube6_3.data.model.PlaylistsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    suspend fun getPlayLists(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResult: Int,
    ): Response<PlaylistsModel>

    @GET("playlistItems")
    suspend fun getPlaylistItem(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResult: Int
    ): Response<PlaylistsModel>
}