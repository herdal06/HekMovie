package com.herdal.moviehouse.ui.movie_details.similar_movies

sealed class SimilarMoviesUiEvent {
    data class GetSimilarMovies(val movieId: Int) : SimilarMoviesUiEvent()
}