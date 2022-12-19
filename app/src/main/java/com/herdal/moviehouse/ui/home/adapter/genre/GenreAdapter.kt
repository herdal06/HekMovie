package com.herdal.moviehouse.ui.home.adapter.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.herdal.moviehouse.databinding.ItemGenreBinding
import com.herdal.moviehouse.domain.uimodel.GenreUiModel

class GenreAdapter : ListAdapter<GenreUiModel, GenreViewHolder>(DiffCallBack) {

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<GenreUiModel>() {
            override fun areItemsTheSame(oldItem: GenreUiModel, newItem: GenreUiModel): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GenreUiModel, newItem: GenreUiModel): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val currentGenre = getItem(position)
        currentGenre?.let {
            holder.bind(currentGenre)
        }
    }
}