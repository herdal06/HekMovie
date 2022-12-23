package com.herdal.moviehouse.data.remote.model.person_detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonDetailDto(
    @Json(name ="adult")
    val adult: Boolean?,
    @Json(name ="also_known_as")
    val also_known_as: List<String>?,
    @Json(name ="biography")
    val biography: String?,
    @Json(name ="birthday")
    val birthday: String?,
    @Json(name ="deathday")
    val deathday: String?,
    @Json(name ="gender")
    val gender: Int?,
    @Json(name ="homepage")
    val homepage: String?,
    @Json(name ="id")
    val id: Int?,
    @Json(name ="imdb_id")
    val imdb_id: String?,
    @Json(name ="known_for_department")
    val known_for_department: String?,
    @Json(name ="name")
    val name: String?,
    @Json(name ="place_of_birth")
    val place_of_birth: String?,
    @Json(name ="popularity")
    val popularity: Double?,
    @Json(name ="profile_path")
    val profile_path: String?
)