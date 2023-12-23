package com.example.android7.data.database.repositories


import com.example.android7.data.database.dao.ItemDao
import com.example.android7.data.mapper.mapToItem
import com.example.android7.data.mapper.mapToItemModelList
import com.example.android7.domain.model.ItemModel
import com.example.android7.domain.utils.Resource
import com.example.android7.domain.repositories.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class ItemRepositoryImp @Inject constructor(
    private val dao: ItemDao
): ItemRepository {

    override suspend fun getAllItems(): Flow<Resource<List<ItemModel>>> =
        flow{
            emit(Resource.Loading())
            try{
                val result = dao.getAllItems()
                emit(Resource.Success(result.mapToItemModelList()))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }

    override suspend fun insertItem(item: ItemModel) : Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(dao.insertItem(item.mapToItem())))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }

    override suspend fun updateItem(item: ItemModel) : Flow<Resource<Unit>>  =
        flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(dao.updateItem(item.mapToItem())))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }

    override suspend fun deleteItem(item: ItemModel) : Flow<Resource<Unit>>  =
        flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(dao.deleteItem(item.mapToItem())))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
}