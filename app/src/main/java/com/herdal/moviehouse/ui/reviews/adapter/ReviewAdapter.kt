package com.herdal.moviehouse.ui.reviews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.herdal.moviehouse.databinding.ItemReviewBinding
import com.herdal.moviehouse.domain.uimodel.ReviewUiModel

class ReviewAdapter(
) : PagingDataAdapter<ReviewUiModel, ReviewViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ReviewUiModel>() {
            override fun areItemsTheSame(
                oldItem: ReviewUiModel,
                newItem: ReviewUiModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ReviewUiModel,
                newItem: ReviewUiModel
            ): Boolean = oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(currentItem) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder =
        ReviewViewHolder(
            ItemReviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}