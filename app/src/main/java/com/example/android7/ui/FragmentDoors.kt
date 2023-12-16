package com.example.android7.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android7.R
import com.example.android7.databinding.FragmentDoorsBinding
import com.example.android7.ui.adapter.RVAdapter
import com.example.android7.data.database.model.Item
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDoors : Fragment() {
    private lateinit var binding: FragmentDoorsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDoorsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.rv.apply {
            layoutManager = LinearLayoutManager(
                this@FragmentDoors.context,
                LinearLayoutManager.VERTICAL,
                false
            )
        }
    }
}