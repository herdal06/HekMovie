package com.herdal.moviehouse.ui.movie_details.similar_movies

import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel

data class SimilarMoviesUiState(
    var error: String? = null,
    val loading: Boolean? = false,
    val movies: List<MovieUiModel>? = emptyList()
)