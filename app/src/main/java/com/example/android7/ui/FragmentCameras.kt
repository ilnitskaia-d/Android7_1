package com.example.android7.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android7.databinding.FragmentCamerasBinding
import com.example.android7.data.database.model.Item
import com.example.android7.domain.model.ItemModel
import com.example.android7.ui.adapter.RVAdapter
import com.example.android7.ui.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentCameras : Fragment() {
    lateinit var binding: FragmentCamerasBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCamerasBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllData()
        val rvAdapter = RVAdapter(
            listOf()
        )

        binding.rv.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(
                this@FragmentCameras.context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }

    }

    private fun getAllData() {
        lifecycleScope.launch {
            viewModel.getAllItems()
            viewModel.items.collect{
                when (it) {
                    is UIState.Empty -> print("progressBar.visibility = gone")
                    is UIState.Error -> print("error")
                    is UIState.Loading -> print("progressBar.visibility = visible")
                    is UIState.Success -> {
                        print("progressBar.visibility = visible")
                        print("adapter.setItemList")
                    }
                }
            }
        }
    }

    private fun insertItem(itemModel: ItemModel) {
        lifecycleScope.launch {
            viewModel.insertItem(itemModel)
            viewModel.insertItemsStatus.collect{
                when (it) {
                    is UIState.Empty -> print("progressBar.visibility = gone")
                    is UIState.Error -> print("error")
                    is UIState.Loading -> print("progressBar.visibility = visible")
                    is UIState.Success -> {
                        print("progressBar.visibility = visible")
                        print("show inserted successfully")
                    }
                }
            }
        }
    }

    private fun deleteItem(itemModel: ItemModel) {
        lifecycleScope.launch {
            viewModel.deleteItem(itemModel)
            viewModel.deleteItemsStatus.collect{
                when (it) {
                    is UIState.Empty -> print("progressBar.visibility = gone")
                    is UIState.Error -> print("error")
                    is UIState.Loading -> print("progressBar.visibility = visible")
                    is UIState.Success -> {
                        print("progressBar.visibility = visible")
                        print("show deleted successfully")
                    }
                }
            }
        }
    }
}