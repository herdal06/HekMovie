package com.herdal.moviehouse.ui.genres.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.databinding.ItemAllGenresBinding
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel
import com.herdal.moviehouse.utils.extensions.executeWithAction

class GenresViewHolder(
    private val binding: ItemAllGenresBinding,
    private val onClickGenre: ((genre: GenreUiModel) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: GenreUiModel) = binding.apply {
        binding.executeWithAction {
            this.genre = genre
        }

        itemView.setOnClickListener {
            onClickGenre?.invoke(genre)
        }
    }
}