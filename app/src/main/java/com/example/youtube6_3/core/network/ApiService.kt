package com.example.youtube6_3.core.network

import com.example.youtube6_3.data.model.PlaylistsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    fun getPlaylists(
        @Query("part")
        part: String,
        @Query("key")
        apiKey: String,
        @Query ("channelId")
        channelId: String,
        @Query("maxResults")
        maxResult: Int,
    ): Call<PlaylistsModel>
}