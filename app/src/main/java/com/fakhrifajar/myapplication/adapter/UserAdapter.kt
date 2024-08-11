package com.fakhrifajar.myapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fakhrifajar.myapplication.data.network.response.DataItem
import com.fakhrifajar.myapplication.databinding.ItemUserBinding

class UserAdapter(private val onItemClick: (DataItem) -> Unit): PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem, onItemClick: (DataItem) -> Unit) {
            Log.d("INI DALEM ADAPTER", "USER ADAPTER")
            binding.apply {
                val fullName = "${data.firstName} ${data.lastName}"
                Glide.with(itemView.context)
                    .load(data.avatar)
                    .circleCrop()
                    .into(userProfileImage)
                username.text = fullName
                email.text = data.email
            }
            itemView.setOnClickListener {
                onItemClick(data)
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data, onItemClick)
            Log.d("DATA ITEM", data.toString())
        }
        else {
            Log.i("DATA ITEM", "NULL")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d("VIEWHOLDER", "USER ADAPTER")
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return MyViewHolder(binding)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}