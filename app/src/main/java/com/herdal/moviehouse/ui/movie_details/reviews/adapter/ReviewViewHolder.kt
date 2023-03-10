package com.herdal.moviehouse.ui.movie_details.reviews.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.moviehouse.databinding.ItemReviewBinding
import com.herdal.moviehouse.domain.uimodel.review.ReviewUiModel
import com.herdal.moviehouse.utils.extensions.executeWithAction

class ReviewViewHolder(
    private val binding: ItemReviewBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(review: ReviewUiModel) = binding.apply {
        binding.executeWithAction {
            this.review = review
        }
    }
}