package com.herdal.moviehouse.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel
import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel
import com.herdal.moviehouse.domain.use_case.genre.GetGenresUseCase
import com.herdal.moviehouse.domain.use_case.movie.GetNowPlayingMoviesUseCase
import com.herdal.moviehouse.domain.use_case.movie.GetPopularMoviesUseCase
import com.herdal.moviehouse.domain.use_case.movie.GetTopRatedMoviesUseCase
import com.herdal.moviehouse.domain.use_case.movie.GetUpcomingMoviesUseCase
import com.herdal.moviehouse.domain.use_case.person.GetPopularPeopleUseCase
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
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularPeopleUseCase: GetPopularPeopleUseCase
) : ViewModel() {
    private val _genres =
        MutableStateFlow<Resource<List<GenreUiModel>>>(Resource.Loading())
    val genres: StateFlow<Resource<List<GenreUiModel>>> = _genres

    private val _popularMovies =
        MutableLiveData<PagingData<MovieUiModel>>()

    private val _topRatedMovies =
        MutableLiveData<PagingData<MovieUiModel>>()

    private val _upcomingMovies =
        MutableLiveData<PagingData<MovieUiModel>>()

    private val _nowPlayingMovies =
        MutableLiveData<PagingData<MovieUiModel>>()

    private val _popularPeople =
        MutableLiveData<PagingData<PersonUiModel>>()

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
        _popularMovies.value = response.value
        Timber.d("$response")
        return response
    }

    fun getTopRatedMovies(): LiveData<PagingData<MovieUiModel>> {
        val response = getTopRatedMoviesUseCase.invoke().cachedIn(viewModelScope).asLiveData()
        _topRatedMovies.value = response.value
        Timber.d("$response")
        return response
    }

    fun getUpcomingMovies(): LiveData<PagingData<MovieUiModel>> {
        val response = getUpcomingMoviesUseCase.invoke().cachedIn(viewModelScope).asLiveData()
        _upcomingMovies.value = response.value
        Timber.d("$response")
        return response
    }

    fun getNowPlayingMovies(): LiveData<PagingData<MovieUiModel>> {
        val response = getNowPlayingMoviesUseCase.invoke().cachedIn(viewModelScope).asLiveData()
        _nowPlayingMovies.value = response.value
        Timber.d("$response")
        return response
    }

    fun getPopularPeople(): LiveData<PagingData<PersonUiModel>> {
        val response = getPopularPeopleUseCase.invoke().cachedIn(viewModelScope).asLiveData()
        _popularPeople.value = response.value
        Timber.d("$response")
        return response
    }
}