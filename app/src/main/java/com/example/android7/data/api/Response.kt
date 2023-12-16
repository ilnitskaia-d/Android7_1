package com.example.android7.data.api

import com.example.android7.domain.model.ItemModel

data class Response(
    val rooms: List<String>,
    val cameras: List<ItemModel>
)
