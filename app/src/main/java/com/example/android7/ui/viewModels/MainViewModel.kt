package com.example.android7.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android7.data.api.CameraApi
import com.example.android7.data.database.repositories.ItemRepositoryImp
import com.example.android7.domain.model.ItemModel
import com.example.android7.domain.usecase.DeleteItemUseCase
import com.example.android7.domain.usecase.GetAllItemsUseCase
import com.example.android7.domain.usecase.InsertItemUseCase
import com.example.android7.domain.utils.Resource
import com.example.android7.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val insertItemUseCase: InsertItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val cameraApi: CameraApi
): ViewModel() {

    private var _items = MutableStateFlow<UIState<List<ItemModel>>>(UIState.Empty())
    val items : StateFlow<UIState<List<ItemModel>>> = _items

    private var _insertItemsStatus = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val insertItemsStatus : StateFlow<UIState<Unit>> = _insertItemsStatus

    private var _deleteItemsStatus = MutableStateFlow<UIState<Unit>>(UIState.Empty())
    val deleteItemsStatus : StateFlow<UIState<Unit>> = _deleteItemsStatus

    suspend fun getAllItems() {
        viewModelScope.launch {
            getAllItemsUseCase.getAllItems().collect {
                when(it) {
                    is Resource.Loading -> _items.value = UIState.Loading()
                    is Resource.Success -> {
                        if(it.data?.isNotEmpty() == true)
                        {
                            _items.value = UIState.Success(it.data)
                        } else {
                            _items.value = UIState.Empty()
                        }
                    }
                    is Resource.Error -> _items.value = UIState.Error(it.message, 404)
                }
            }
        }
    }

    suspend fun insertItem(itemModel: ItemModel) {
        viewModelScope.launch {
            insertItemUseCase.insertItem(itemModel).collect{
                when(it) {
                    is Resource.Loading -> _insertItemsStatus.value = UIState.Loading()
                    is Resource.Success -> _insertItemsStatus.value = UIState.Success(it.data)
                    is Resource.Error -> _insertItemsStatus.value = UIState.Error(it.message, 404)
                }
            }
        }
    }

    suspend fun deleteItem(itemModel: ItemModel) {
        viewModelScope.launch {
            deleteItemUseCase.deleteItem(itemModel).collect{
                when(it) {
                    is Resource.Loading -> _deleteItemsStatus .value = UIState.Loading()
                    is Resource.Success -> _deleteItemsStatus .value = UIState.Success(it.data)
                    is Resource.Error -> _deleteItemsStatus .value = UIState.Error(it.message, 404)
                }
            }
        }
    }
}