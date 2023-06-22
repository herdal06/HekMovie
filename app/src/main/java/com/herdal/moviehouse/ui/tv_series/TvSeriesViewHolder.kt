package com.herdal.moviehouse.ui.tv_series

import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.databinding.ItemTvSeriesBinding
import com.herdal.moviehouse.domain.uimodel.tv_series.TvSeriesUiModel
import com.herdal.moviehouse.utils.extensions.executeWithAction

class TvSeriesViewHolder(
    private val binding: ItemTvSeriesBinding,
    private val onClickTvSeries: ((tvSeriesId: Int) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(tvSeries:TvSeriesUiModel) {
        binding.executeWithAction {
            this.tvSeries = tvSeries
        }

        itemView.setOnClickListener {
            tvSeries.id?.let { it1 -> onClickTvSeries?.invoke(it1) }
        }
    }
}