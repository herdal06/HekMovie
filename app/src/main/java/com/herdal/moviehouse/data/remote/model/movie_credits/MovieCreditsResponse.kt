package com.herdal.moviehouse.data.remote.model.movie_credits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCreditsResponse(
    @Json(name ="cast")
    val cast: List<CastDto>,
    @Json(name ="crew")
    val crew: List<CrewDto>,
    @Json(name ="id")
    val id: Int?
)