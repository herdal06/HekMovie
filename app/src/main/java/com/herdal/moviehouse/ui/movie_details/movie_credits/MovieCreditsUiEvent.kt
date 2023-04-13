package com.herdal.moviehouse.ui.movie_details.movie_credits

sealed class MovieCreditsUiEvent {
    data class GetMovieCredits(val movieId: Int) : MovieCreditsUiEvent()
}