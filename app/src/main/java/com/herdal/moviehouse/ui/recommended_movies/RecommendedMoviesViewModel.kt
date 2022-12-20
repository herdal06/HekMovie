package com.herdal.moviehouse.ui.recommended_movies

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.herdal.moviehouse.domain.uimodel.MovieUiModel
import com.herdal.moviehouse.domain.use_case.movie.GetRecommendedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecommendedMoviesViewModel @Inject constructor(
    private val getRecommendedMoviesUseCase: GetRecommendedMoviesUseCase
) : ViewModel() {

    private val _recommendedMovies =
        MutableLiveData<PagingData<MovieUiModel>>()

    fun getRecommendedMovies(movieId: Int): LiveData<PagingData<MovieUiModel>> {
        val response = getRecommendedMoviesUseCase.invoke(movieId).cachedIn(viewModelScope).asLiveData()
        _recommendedMovies.value = response.value
        Timber.d("$response")
        return response
    }
}