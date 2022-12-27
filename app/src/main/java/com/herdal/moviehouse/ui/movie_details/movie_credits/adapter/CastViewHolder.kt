package com.herdal.moviehouse.ui.movie_details.movie_credits.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.databinding.ItemCastBinding
import com.herdal.moviehouse.domain.uimodel.movie_credits.CastUiModel
import com.herdal.moviehouse.utils.extensions.executeWithAction

class CastViewHolder(
    private val binding: ItemCastBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cast: CastUiModel) = binding.apply {
        binding.executeWithAction {
            this.cast = cast
        }
    }
}