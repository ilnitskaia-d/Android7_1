package com.example.android7.data.mapper

import com.example.android7.data.database.model.Item
import com.example.android7.domain.model.ItemModel

fun ItemModel.mapToItem() = Item(
    id = id,
    name = name,
    snapshot = snapshot,
    room = room,
    favorite = favorite,
    rec = rec
)

fun List<Item>.mapToItemModelList() = this.map {
    ItemModel(
        id = it.id,
        name = it.name,
        snapshot = it.snapshot,
        room = it.room,
        favorite = it.favorite,
        rec = it.rec
    )
}