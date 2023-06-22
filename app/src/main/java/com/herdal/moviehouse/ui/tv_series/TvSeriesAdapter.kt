package com.herdal.moviehouse.ui.tv_series

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.common.base.BasePagingAdapter
import com.herdal.moviehouse.databinding.ItemTvSeriesBinding
import com.herdal.moviehouse.domain.uimodel.tv_series.TvSeriesUiModel

class TvSeriesAdapter(
    private val onClickTvSeries: ((tvSeriesId: Int) -> Unit)?
) : BasePagingAdapter<TvSeriesUiModel>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        TvSeriesViewHolder(
            ItemTvSeriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickTvSeries
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TvSeriesViewHolder -> {
                getItem(position)?.let { tvSeries -> holder.bind(tvSeries) }
            }
        }
    }
}