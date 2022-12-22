package com.herdal.moviehouse.data.remote.model.person

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonDto(
    @Json(name ="gender")
    val gender: Int?,
    @Json(name ="id")
    val id: Int?,
    @Json(name ="known_for")
    val known_for: List<KnownForDto>?,
    @Json(name ="known_for_department")
    val known_for_department: String?,
    @Json(name ="name")
    val name: String?,
    @Json(name ="popularity")
    val popularity: Double?,
    @Json(name ="profile_path")
    val profile_path: String?
)