package com.herdal.moviehouse.domain.use_case.review

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.repository.ReviewRepository
import com.herdal.moviehouse.domain.uimodel.ReviewUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReviewsForMovieUseCase @Inject constructor(
    private val reviewRepository: ReviewRepository
) {
    operator fun invoke(movieId: Int): Flow<PagingData<ReviewUiModel>> {
        return reviewRepository.getReviewsForMovie(movieId = movieId)
    }
}