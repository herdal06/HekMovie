package com.herdal.moviehouse.ui.movie_details

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.uimodel.MovieDetailUiModel
import com.herdal.moviehouse.domain.uimodel.MovieUiModel
import com.herdal.moviehouse.domain.use_case.movie.GetMovieDetailsUseCase
import com.herdal.moviehouse.domain.use_case.movie.GetRecommendedMoviesUseCase
import com.herdal.moviehouse.domain.use_case.movie.GetSimilarMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val getRecommendedMoviesUseCase: GetRecommendedMoviesUseCase
) : ViewModel() {

    private val _movieDetail =
        MutableStateFlow<Resource<MovieDetailUiModel>>(Resource.Loading())
    val movieDetail: StateFlow<Resource<MovieDetailUiModel>> = _movieDetail

    private val _similarMovies =
        MutableLiveData<PagingData<MovieUiModel>>()

    private val _recommendedMovies =
        MutableLiveData<PagingData<MovieUiModel>>()

    fun getMovieDetails(id: Int) {
        getMovieDetailsUseCase.invoke(id)
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _movieDetail.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        if (resource.data != null) {
                            _movieDetail.value = Resource.Success(resource.data)
                        }
                    }
                    is Resource.Error -> {
                        _movieDetail.value = Resource.Error(resource.message)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun getSimilarMovies(movieId: Int): LiveData<PagingData<MovieUiModel>> {
        val response = getSimilarMoviesUseCase.invoke(movieId).cachedIn(viewModelScope).asLiveData()
        _similarMovies.value = response.value
        Timber.d("$response")
        return response
    }

    fun getRecommendedMovies(movieId: Int): LiveData<PagingData<MovieUiModel>> {
        val response =
            getRecommendedMoviesUseCase.invoke(movieId).cachedIn(viewModelScope).asLiveData()
        _recommendedMovies.value = response.value
        Timber.d("$response")
        return response
    }
}