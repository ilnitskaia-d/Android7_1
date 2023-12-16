package com.example.android7.domain.repositories

import com.example.android7.data.api.Response
import com.example.android7.data.database.model.Item
import com.example.android7.domain.model.ItemModel
import com.example.android7.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun getCameras(): Flow<Response>

    suspend fun getAllItems(): Flow<Resource<List<ItemModel>>>

    suspend fun insertItem(item: ItemModel) : Flow<Resource<Unit>>

    suspend fun updateItem(item: ItemModel) : Flow<Resource<Unit>>

    suspend fun deleteItem(item: ItemModel) : Flow<Resource<Unit>>
}