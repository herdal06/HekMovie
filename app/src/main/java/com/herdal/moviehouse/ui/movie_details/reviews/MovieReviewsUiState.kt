package com.herdal.moviehouse.ui.movie_details.reviews

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.uimodel.review.ReviewUiModel

data class MovieReviewsUiState(
    var error: String? = null,
    val loading: Boolean? = false,
    val reviews: PagingData<ReviewUiModel>? = PagingData.empty()
)