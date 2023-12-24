package com.example.android7.data.api

import com.example.android7.data.database.model.ResponseCamera
import retrofit2.Call
import retrofit2.http.GET

interface CameraApi {
    @GET("cameras/")
    fun getCameras():Call<ResponseCamera>
}