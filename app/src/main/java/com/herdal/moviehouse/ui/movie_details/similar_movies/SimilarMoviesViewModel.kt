package com.herdal.moviehouse.ui.movie_details.similar_movies

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel
import com.herdal.moviehouse.domain.use_case.movie.GetSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel @Inject constructor(
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : ViewModel() {

    private val _similarMovies =
        MutableLiveData<PagingData<MovieUiModel>>()

    fun getSimilarMovies(movieId: Int): LiveData<PagingData<MovieUiModel>> {
        val response = getSimilarMoviesUseCase.invoke(movieId).cachedIn(viewModelScope).asLiveData()
        _similarMovies.value = response.value
        Timber.d("$response")
        return response
    }
}