package com.example.android7.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        binding.rv.apply {
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
                    is UIState.Empty -> binding.progressBar.visibility = View.GONE
                    is UIState.Error -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        binding.progressBar.visibility = View.GONE
                    }
                    is UIState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is UIState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rv.adapter = RVAdapter(it.data!!)
                        Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show()
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
                    is UIState.Empty -> binding.progressBar.visibility = View.GONE
                    is UIState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    }
                    is UIState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is UIState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Successfully inserted", Toast.LENGTH_LONG).show()
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
                    is UIState.Empty -> binding.progressBar.visibility = View.GONE
                    is UIState.Error -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        binding.progressBar.visibility = View.GONE
                    }
                    is UIState.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is UIState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Successfully deleted", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}