package com.herdal.moviehouse.domain.repository

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.uimodel.review.ReviewUiModel
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    fun getReviewsForMovie(movieId: Int): Flow<PagingData<ReviewUiModel>>
}