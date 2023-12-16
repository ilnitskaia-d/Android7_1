package com.example.android7.ui.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.android7.databinding.ItemLayoutBinding
import com.example.android7.data.database.model.Item


class RVAdapter(private val list: List<Item>): Adapter<RVAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
                ItemLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ItemViewHolder(private val binding: ItemLayoutBinding):ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                tvCamera.text = item.name
                Glide.with(binding.root).load(item.snapshot).centerCrop().into(ivCamera)
                if(item.rec != true) {
                    ivRec.visibility = GONE
                }
                if(!item.favorite) {
                    ivFav.visibility = GONE
                }
            }
        }
    }
}
