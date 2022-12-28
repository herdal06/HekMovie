package com.herdal.moviehouse.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.herdal.moviehouse.common.data_source.ReviewDataSource
import com.herdal.moviehouse.common.mapper.review.ReviewMapper
import com.herdal.moviehouse.domain.repository.ReviewRepository
import com.herdal.moviehouse.domain.uimodel.review.ReviewUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewMapper: ReviewMapper,
    private val remote: ReviewDataSource.Remote
) : ReviewRepository {
    override fun getReviewsForMovie(movieId: Int): Flow<PagingData<ReviewUiModel>> {
        val domainReview = remote.getReviewsForMovie(movieId).map { pagingData ->
            pagingData.map { remoteReview ->
                reviewMapper.toDomain(remoteReview)
            }
        }
        Timber.d("popular: $domainReview")
        return domainReview
    }
}