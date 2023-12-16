package com.example.android7.domain.model

data class ItemModel(
    val id: Int,
    val name: String,
    val snapshot: String?,
    val room: String?,
    val favorite: Boolean,
    val rec: Boolean? = false
)
