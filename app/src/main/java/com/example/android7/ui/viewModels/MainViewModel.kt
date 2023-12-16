package com.example.android7.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android7.data.api.Response
import com.example.android7.data.database.repositories.ItemRepositoryImp
import com.example.android7.domain.model.ItemModel
import com.example.android7.domain.repositories.ItemRepository
import com.example.android7.domain.usecase.GetAllItemsUseCase
import com.example.android7.domain.utils.Resource
import com.example.android7.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllItemsUseCase: GetAllItemsUseCase
): ViewModel() {

    private var _items = MutableStateFlow<UIState<List<ItemModel>>>(UIState.Empty())
    val items : StateFlow<UIState<List<ItemModel>>> = _items

    suspend fun getAllItems() {
        viewModelScope.launch {
            getAllItemsUseCase.getAllItems().collect {
                when(it) {
                    is Resource.Loading -> _items.value = UIState.Loading()
                    is Resource.Success -> _items.value = UIState.Success(it.data)
                    is Resource.Error -> _items.value = UIState.Error(it.message, 404)
                }
            }
        }
    }

}