package com.herdal.moviehouse.data.data_source

import androidx.paging.PagingData
import com.herdal.moviehouse.data.remote.model.review.ReviewDto
import kotlinx.coroutines.flow.Flow

interface ReviewDataSource {
    interface Remote {
        fun getReviewsForMovie(movieId: Int): Flow<PagingData<ReviewDto>>
    }
}