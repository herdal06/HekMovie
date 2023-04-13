package com.herdal.moviehouse.ui.movie_details.movie_credits

import com.herdal.moviehouse.domain.uimodel.movie_credits.MovieCreditsUiModel

data class MovieCreditsUiState(
    var error: String? = null,
    val loading: Boolean? = false,
    val movieCredits: MovieCreditsUiModel? = null
)