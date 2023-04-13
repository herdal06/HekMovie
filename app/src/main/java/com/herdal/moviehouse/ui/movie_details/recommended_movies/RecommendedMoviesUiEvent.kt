package com.herdal.moviehouse.ui.movie_details.recommended_movies

sealed class RecommendedMoviesUiEvent {
    data class GetRecommendedMovies(val movieId: Int) : RecommendedMoviesUiEvent()
}