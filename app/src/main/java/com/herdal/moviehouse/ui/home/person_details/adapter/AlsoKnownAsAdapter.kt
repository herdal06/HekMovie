package com.herdal.moviehouse.ui.home.person_details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.herdal.moviehouse.databinding.ItemAlsoKnownAsBinding

class AlsoKnownAsAdapter : ListAdapter<String, AlsoKnownAsViewHolder>(DiffCallBack) {

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlsoKnownAsViewHolder =
        AlsoKnownAsViewHolder(
            ItemAlsoKnownAsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AlsoKnownAsViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(currentItem)
        }
    }
}