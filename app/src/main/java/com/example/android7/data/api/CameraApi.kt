package com.example.android7.data.api

import com.example.android7.domain.model.ItemModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CameraApi {
    @GET("data")
    suspend fun getCameras(): Response
}