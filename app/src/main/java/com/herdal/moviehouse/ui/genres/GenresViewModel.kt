package com.herdal.moviehouse.ui.genres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel
import com.herdal.moviehouse.domain.use_case.genre.GetGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    private val _genres =
        MutableStateFlow<Resource<List<GenreUiModel>>>(Resource.Loading())
    val genres: StateFlow<Resource<List<GenreUiModel>>> = _genres

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
}