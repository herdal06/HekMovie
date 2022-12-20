package com.herdal.moviehouse.ui.home.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.herdal.moviehouse.databinding.ItemMovieBinding
import com.herdal.moviehouse.domain.uimodel.MovieUiModel

class MovieAdapter(
    private val onClickMovie: ((movieId: Int) -> Unit)?
) : PagingDataAdapter<MovieUiModel, MovieViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<MovieUiModel>() {
            override fun areItemsTheSame(
                oldItem: MovieUiModel,
                newItem: MovieUiModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieUiModel,
                newItem: MovieUiModel
            ): Boolean = oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(currentItem) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickMovie
        )
}