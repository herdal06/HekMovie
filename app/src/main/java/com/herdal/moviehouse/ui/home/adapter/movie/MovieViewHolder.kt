package com.herdal.moviehouse.ui.home.adapter.movie

import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.databinding.ItemMovieBinding
import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel
import com.herdal.moviehouse.utils.extensions.executeWithAction

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val onClickMovieListener: OnClickMovieListener?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieUiModel) = binding.apply {
        binding.executeWithAction {
            this.movie = movie
        }

        root.setOnClickListener {
            movie.id?.let {
                onClickMovieListener?.onClick(it)
            }
        }
    }
}