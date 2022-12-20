package com.herdal.moviehouse.ui.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.uimodel.MovieDetailUiModel
import com.herdal.moviehouse.domain.use_case.movie.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _movieDetail =
        MutableStateFlow<Resource<MovieDetailUiModel>>(Resource.Loading())
    val movieDetail: StateFlow<Resource<MovieDetailUiModel>> = _movieDetail

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
}