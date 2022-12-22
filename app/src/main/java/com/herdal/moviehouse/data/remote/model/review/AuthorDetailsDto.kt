package com.herdal.moviehouse.data.remote.model.review

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorDetailsDto(
    @Json(name = "avatar_path")
    val avatar_path: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "rating")
    val rating: Int?,
    @Json(name = "username")
    val username: String
)