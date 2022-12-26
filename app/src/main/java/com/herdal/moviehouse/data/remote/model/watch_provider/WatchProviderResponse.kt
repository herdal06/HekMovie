package com.herdal.moviehouse.data.remote.model.watch_provider

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WatchProviderResponse(
    @Json(name ="id")
    val id: Int,
    @Json(name ="results")
    val results: WatchProviderDto
)