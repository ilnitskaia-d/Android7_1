package com.example.android7.data.database.model

import com.example.android7.domain.model.ItemModel

data class ResponseCamera(
    val items: List<ItemModel>,
    val room: List<String>
)