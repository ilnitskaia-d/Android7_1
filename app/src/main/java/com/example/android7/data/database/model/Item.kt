package com.example.android7.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val snapshot: String?,
    val room: String?,
    val favorite: Boolean,
    val rec: Boolean? = false
)
