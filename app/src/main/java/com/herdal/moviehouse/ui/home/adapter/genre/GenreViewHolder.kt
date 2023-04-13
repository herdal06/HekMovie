package com.herdal.moviehouse.ui.home.adapter.genre

import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.databinding.ItemGenreBinding
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel
import com.herdal.moviehouse.utils.extensions.executeWithAction

class GenreViewHolder(
    private val binding: ItemGenreBinding,
    private val onClickGenreListener: OnClickGenreListener?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: GenreUiModel) = binding.apply {
        binding.executeWithAction {
            this.genre = genre
        }

        root.setOnClickListener {
            onClickGenreListener?.onClick(genre)
        }
    }
}