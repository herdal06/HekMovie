package com.herdal.moviehouse.data.remote.model.video

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoDto(
    @Json(name ="id")
    val id: String?,
    @Json(name ="iso_3166_1")
    val iso_3166_1: String?,
    @Json(name ="iso_639_1")
    val iso_639_1: String?,
    @Json(name ="key")
    val key: String?,
    @Json(name ="name")
    val name: String?,
    @Json(name ="official")
    val official: Boolean?,
    @Json(name ="published_at")
    val published_at: String?,
    @Json(name ="site")
    val site: String?,
    @Json(name ="size")
    val size: Int?,
    @Json(name ="type")
    val type: String?
)