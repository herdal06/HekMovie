package com.herdal.moviehouse.ui.movie_details.movie_credits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.uimodel.movie_credits.MovieCreditsUiModel
import com.herdal.moviehouse.domain.use_case.movie.GetMovieCreditsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieCreditsViewModel @Inject constructor(
    private val getMovieCreditsUseCase: GetMovieCreditsUseCase
) : ViewModel() {

    private val _movieCredits =
        MutableStateFlow<Resource<MovieCreditsUiModel>>(Resource.Loading())
    val movieCredits: StateFlow<Resource<MovieCreditsUiModel>> = _movieCredits

    fun getMovieCredits(movieId: Int) {
        getMovieCreditsUseCase.invoke(movieId)
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _movieCredits.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        if (resource.data != null) {
                            _movieCredits.value = Resource.Success(resource.data)
                        }
                    }
                    is Resource.Error -> {
                        _movieCredits.value = Resource.Error(resource.message)
                    }
                }
            }.launchIn(viewModelScope)
    }
}