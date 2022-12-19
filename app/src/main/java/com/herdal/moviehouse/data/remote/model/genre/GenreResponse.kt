package com.herdal.moviehouse.data.remote.model.genre

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResponse(
    @Json(name = "genres")
    val genres: List<GenreDto>
)