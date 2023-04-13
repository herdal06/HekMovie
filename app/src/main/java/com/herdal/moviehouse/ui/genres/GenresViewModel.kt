package com.herdal.moviehouse.ui.genres

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.use_case.genre.GetGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GenresUiState())
    val uiState: StateFlow<GenresUiState> = _uiState

    fun onEvent(event: GenresUiEvent) {
        when (event) {
            is GenresUiEvent.GetAllGenres -> {
                getAllGenres()
            }
        }
    }

    private fun getAllGenres() {
        getGenresUseCase.invoke().onEach { resource ->
            Log.d("GenresViewModel", "fetched")
            when (resource) {
                is Resource.Loading -> {
                    Log.d("GenresViewModel", "fetched in loading")
                    _uiState.update { state ->
                        state.copy(loading = true)
                    }
                }
                is Resource.Success -> {
                    Log.d("GenresViewModel", "fetched in success")
                    _uiState.update { state ->
                        state.copy(genres = resource.data)
                    }
                }
                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(error = resource.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}