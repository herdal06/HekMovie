package com.herdal.moviehouse.data.remote.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.herdal.moviehouse.common.data_source.ReviewDataSource
import com.herdal.moviehouse.data.remote.model.review.ReviewDto
import com.herdal.moviehouse.data.remote.paging_source.ReviewPagingSource
import com.herdal.moviehouse.data.remote.service.ReviewService
import com.herdal.moviehouse.utils.ApiConstants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewRemoteDataSource @Inject constructor(
    private val reviewService: ReviewService
) : ReviewDataSource.Remote {
    override fun getReviewsForMovie(movieId: Int): Flow<PagingData<ReviewDto>> =
        Pager(
            config = PagingConfig(
                pageSize = ApiConstants.NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                ReviewPagingSource(
                    reviewService,
                    movieId
                )
            }
        ).flow
}