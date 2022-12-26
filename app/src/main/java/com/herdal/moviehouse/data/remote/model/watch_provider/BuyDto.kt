package com.herdal.moviehouse.data.remote.model.watch_provider

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BuyDto(
    @Json(name ="logo_path")
    val logo_path: String,
    @Json(name ="provider_id")
    val provider_id: Int,
    @Json(name ="provider_name")
    val provider_name: String
)