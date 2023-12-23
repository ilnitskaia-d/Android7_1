package com.example.android7.domain.usecase

import com.example.android7.domain.repositories.ItemRepository
import javax.inject.Inject

class GetAllItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    suspend fun getAllItems() = itemRepository.getAllItems()
}