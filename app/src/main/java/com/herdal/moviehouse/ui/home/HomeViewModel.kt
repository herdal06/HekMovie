package com.herdal.moviehouse.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.uimodel.GenreUiModel
import com.herdal.moviehouse.domain.uimodel.MovieUiModel
import com.herdal.moviehouse.domain.use_case.genre.GetGenresUseCase
import com.herdal.moviehouse.domain.use_case.movie.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : ViewModel() {
    private val _genres =
        MutableStateFlow<Resource<List<GenreUiModel>>>(Resource.Loading())
    val genres: StateFlow<Resource<List<GenreUiModel>>> = _genres

    private val _popularMovies =
        MutableStateFlow<Resource<PagingData<MovieUiModel>>>(Resource.Loading())
    val popularMovies: StateFlow<Resource<PagingData<MovieUiModel>>> = _popularMovies

    private val _movies =
        MutableLiveData<PagingData<MovieUiModel>>()
    val movies: LiveData<PagingData<MovieUiModel>> = _movies

    fun getAllGenres() {
        getGenresUseCase.invoke()
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _genres.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        if (resource.data != null) {
                            _genres.value = Resource.Success(resource.data)
                        }
                    }
                    is Resource.Error -> {
                        _genres.value = Resource.Error(resource.message)
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun getPopularMovies(): LiveData<PagingData<MovieUiModel>> {
        val response = getPopularMoviesUseCase.invoke().cachedIn(viewModelScope).asLiveData()
        _movies.value = response.value
        Timber.d("$response")
        return response
    }
}