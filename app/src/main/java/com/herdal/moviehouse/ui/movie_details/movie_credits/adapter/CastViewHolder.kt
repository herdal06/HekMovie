package com.herdal.moviehouse.ui.movie_details.movie_credits.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.data.remote.model.movie_credits.CastDto
import com.herdal.moviehouse.databinding.ItemCastBinding
import com.herdal.moviehouse.utils.extensions.executeWithAction

class CastViewHolder(
    private val binding: ItemCastBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cast: CastDto) = binding.apply {
        binding.executeWithAction {
            this.cast = cast
        }
    }
}