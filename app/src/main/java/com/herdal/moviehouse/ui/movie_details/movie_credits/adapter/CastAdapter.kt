package com.herdal.moviehouse.ui.movie_details.movie_credits.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.common.base.BaseListAdapter
import com.herdal.moviehouse.databinding.ItemCastBinding
import com.herdal.moviehouse.domain.uimodel.movie_credits.CastUiModel

class CastAdapter : BaseListAdapter<CastUiModel>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        CastViewHolder(
            ItemCastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CastViewHolder -> {
                getItem(position)?.let { cast -> holder.bind(cast) }
            }
        }
    }
}