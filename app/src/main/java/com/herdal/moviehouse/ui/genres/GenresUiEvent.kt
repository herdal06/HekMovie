package com.herdal.moviehouse.ui.genres

sealed class GenresUiEvent {
    object GetAllGenres : GenresUiEvent()
}