package com.herdal.moviehouse.ui.search

sealed class SearchUiEvent {
    data class SearchMovies(val searchQuery: String) : SearchUiEvent()
    data class SearchPeople(val searchQuery: String) : SearchUiEvent()
}