package com.herdal.moviehouse.ui.movie_details.reviews

sealed class MovieReviewsUiEvent {
    data class GetReviews(val movieId: Int) : MovieReviewsUiEvent()
}