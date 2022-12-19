package com.herdal.moviehouse.data.remote.model.genre

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)