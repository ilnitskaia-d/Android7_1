package com.example.android7.domain.usecase

import com.example.android7.domain.model.ItemModel
import com.example.android7.domain.repositories.ItemRepository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(
    private val repo: ItemRepository
) {
    suspend fun deleteItem(itemModel: ItemModel) =
        repo.deleteItem(itemModel)
}