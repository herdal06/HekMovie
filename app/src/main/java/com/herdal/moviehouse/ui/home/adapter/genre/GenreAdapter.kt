package com.herdal.moviehouse.ui.home.adapter.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.common.base.BaseListAdapter
import com.herdal.moviehouse.databinding.ItemGenreBinding
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel

class GenreAdapter(
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
        GenreViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickGenre
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GenreViewHolder -> {
                getItem(position)?.let { genre -> holder.bind(genre) }
            }
        }
    }
}