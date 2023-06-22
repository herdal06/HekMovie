package com.herdal.moviehouse.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.use_case.movie.SearchMoviesUseCase
import com.herdal.moviehouse.domain.use_case.person.SearchPeopleUseCase
import com.herdal.moviehouse.domain.use_case.tv_series.SearchTvSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val searchPeopleUseCase: SearchPeopleUseCase,
    private val searchTvSeriesUseCase: SearchTvSeriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun handleEvent(uiEvent: SearchUiEvent) {
        when (uiEvent) {
            is SearchUiEvent.SearchMovies -> searchMovies(uiEvent.searchQuery)
            is SearchUiEvent.SearchPeople -> searchPeople(uiEvent.searchQuery)
            is SearchUiEvent.SearchTvSeries -> searchTvSeries(uiEvent.searchQuery)
        }
    }

    private fun searchMovies(query: String) = viewModelScope.launch {
        if (query.isNotEmpty()) {
            _uiState.update { it.copy(loading = true) }
            searchMoviesUseCase(query).collect { res ->
                when (res) {
                    is Resource.Error -> _uiState.update { it.copy(error = res.message, loading = false) }
                    is Resource.Loading -> _uiState.update { it.copy(loading = true) }
                    is Resource.Success -> _uiState.update { it.copy(movies = res.data, loading = false) }
                }
            }
        }
    }

    private fun searchPeople(query: String) = viewModelScope.launch {
        if (query.isNotEmpty()) {
            _uiState.update { it.copy(loading = true) }
            searchPeopleUseCase(query).collect { res ->
                when (res) {
                    is Resource.Error -> _uiState.update { it.copy(error = res.message, loading = false) }
                    is Resource.Loading -> _uiState.update { it.copy(loading = true) }
                    is Resource.Success -> _uiState.update { it.copy(people = res.data, loading = false) }
                }
            }
        }
    }

    private fun searchTvSeries(query: String) = viewModelScope.launch {
        if (query.isNotEmpty()) {
            _uiState.update { it.copy(loading = true) }
            searchTvSeriesUseCase(query).collect { res ->
                when (res) {
                    is Resource.Error -> _uiState.update { it.copy(error = res.message, loading = false) }
                    is Resource.Loading -> _uiState.update { it.copy(loading = true) }
                    is Resource.Success -> _uiState.update { it.copy(tvSeries = res.data, loading = false) }
                }
            }
        }
    }
}