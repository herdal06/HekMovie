package com.herdal.moviehouse.data.remote.model.video

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoResponse(
    @Json(name ="id")
    val id: Int,
    @Json(name ="results")
    val results: List<VideoDto>
)