package com.herdal.moviehouse.ui.movie_details.reviews

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.herdal.moviehouse.domain.uimodel.review.ReviewUiModel
import com.herdal.moviehouse.domain.use_case.review.GetReviewsForMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieReviewsViewModel @Inject constructor(
    private val getReviewsForMovieUseCase: GetReviewsForMovieUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieReviewsUiState())
    val uiState: StateFlow<MovieReviewsUiState> = _uiState

    fun onEvent(event: MovieReviewsUiEvent) {
        when (event) {
            is MovieReviewsUiEvent.GetReviews -> getReviews(event.movieId)
        }
    }

    private val _reviewsResponse =
        MutableLiveData<PagingData<ReviewUiModel>>()

    fun getReviews(movieId: Int): LiveData<PagingData<ReviewUiModel>> {
        val response =
            getReviewsForMovieUseCase.invoke(movieId).cachedIn(viewModelScope).asLiveData()
        _reviewsResponse.value = response.value
        Timber.d("$response")
        return response
    }
}