package com.herdal.moviehouse.ui.movie_details.reviews

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.herdal.moviehouse.domain.uimodel.review.ReviewUiModel
import com.herdal.moviehouse.domain.use_case.review.GetReviewsForMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieReviewsViewModel @Inject constructor(
    private val getReviewsForMovieUseCase: GetReviewsForMovieUseCase
) : ViewModel() {

    private val _reviewsResponse =
        MutableLiveData<PagingData<ReviewUiModel>>()

    fun getReviews(movieId: Int): LiveData<PagingData<ReviewUiModel>> {
        val response = getReviewsForMovieUseCase.invoke(movieId).cachedIn(viewModelScope).asLiveData()
        _reviewsResponse.value = response.value
        Timber.d("$response")
        return response
    }
}