package com.herdal.moviehouse.data.remote.model.movie_credits

data class MovieCreditsResponse(
    val cast: List<CastDto>,
    val crew: List<CrewDto>,
    val id: Int
)