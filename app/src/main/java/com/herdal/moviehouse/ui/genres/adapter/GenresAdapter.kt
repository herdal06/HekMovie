package com.herdal.moviehouse.ui.genres.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.common.base.BaseListAdapter
import com.herdal.moviehouse.databinding.ItemAllGenresBinding
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel


class GenresAdapter(
    private val onClickGenre: ((genre: GenreUiModel) -> Unit)?
) : BaseListAdapter<GenreUiModel>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        GenresViewHolder(
            ItemAllGenresBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickGenre
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GenresViewHolder -> {
                getItem(position)?.let { genre -> holder.bind(genre) }
            }
        }
    }
}