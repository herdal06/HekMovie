package com.herdal.moviehouse.ui.movies_by_genre

sealed class MoviesByGenreUiEvent {
    data class GetMoviesByGenre(val genreId: Int) : MoviesByGenreUiEvent()
}