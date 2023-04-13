package com.herdal.moviehouse.ui.genres

import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel

data class GenresUiState(
    var error: String? = null,
    val loading: Boolean? = false,
    val genres: List<GenreUiModel>? = null
)

