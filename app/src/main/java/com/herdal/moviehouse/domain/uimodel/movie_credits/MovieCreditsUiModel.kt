package com.herdal.moviehouse.domain.uimodel.movie_credits

data class MovieCreditsUiModel(
    val cast: List<CastUiModel>,
    val crew: List<CrewUiModel>,
    val id: Int
)