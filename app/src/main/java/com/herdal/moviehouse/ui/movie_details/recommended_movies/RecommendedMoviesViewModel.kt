package com.herdal.moviehouse.ui.movie_details.recommended_movies

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel
import com.herdal.moviehouse.domain.use_case.movie.GetRecommendedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecommendedMoviesViewModel @Inject constructor(
    private val getRecommendedMoviesUseCase: GetRecommendedMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecommendedMoviesUiState())
    val uiState: StateFlow<RecommendedMoviesUiState> = _uiState

    fun onEvent(event: RecommendedMoviesUiEvent) {
        when (event) {
            is RecommendedMoviesUiEvent.GetRecommendedMovies -> getRecommendedMovies(event.movieId)
        }
    }

    private val _recommendedMovies =
        MutableLiveData<PagingData<MovieUiModel>>()

    fun getRecommendedMovies(movieId: Int): LiveData<PagingData<MovieUiModel>> {
        val response =
            getRecommendedMoviesUseCase.invoke(movieId).cachedIn(viewModelScope).asLiveData()
        _recommendedMovies.value = response.value
        Timber.d("$response")
        return response
    }
}