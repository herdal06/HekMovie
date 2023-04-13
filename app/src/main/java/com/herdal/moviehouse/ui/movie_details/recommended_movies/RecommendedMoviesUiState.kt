package com.herdal.moviehouse.ui.movie_details.recommended_movies

import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel

data class RecommendedMoviesUiState(
    var error: String? = null,
    val loading: Boolean? = false,
    val movies: List<MovieUiModel>? = emptyList()
)
