package com.herdal.moviehouse.ui.home.person_details.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.databinding.ItemAlsoKnownAsBinding

class AlsoKnownAsViewHolder(
    private val binding: ItemAlsoKnownAsBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(alsoKnownAs: String) = binding.apply {
        tvKnownAs.text = alsoKnownAs
    }
}