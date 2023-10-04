package com.example.youtube6_3.domain.repositoty

import androidx.lifecycle.LiveData
import com.example.youtube6_3.data.model.PlaylistsModel
import com.example.youtube6_3.utils.Constants
import androidx.lifecycle.MutableLiveData
import com.example.youtube6_3.BuildConfig
import com.example.youtube6_3.core.network.ApiService
import com.example.youtube6_3.core.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(private val apiService: ApiService) {

    fun getPlaylists(): LiveData<Resource<PlaylistsModel>> {
        val resourceData = MutableLiveData<Resource<PlaylistsModel>>()
        apiService.getPlaylists(
            part = Constants.PART,
            channelId = Constants.CHANNEL_ID,
            apiKey = BuildConfig.API_KEY,
            maxResult = 11,
        ).enqueue(
            object : Callback<PlaylistsModel> {
                override fun onResponse(
                    call: Call<PlaylistsModel>,
                    response: Response<PlaylistsModel>,
                ) {
                    if (response.isSuccessful) {
                        resourceData.value = Resource.success(response.body())
                    } else {
                        resourceData.value = Resource.error(
                            msg = response.message().toString(),
                            data = null,
                            code = 429
                        )
                    }
                }

                override fun onFailure(call: Call<PlaylistsModel>, t: Throwable) {
                    resourceData.value = Resource.error(
                        msg = t.message.toString(),
                        data = null,
                        code = 429
                    )
                }
            }
        )
        return resourceData
    }
}