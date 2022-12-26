package com.herdal.moviehouse.data.remote.model.watch_provider

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class USDto(
    @Json(name ="buy")
    val buy: List<BuyDto>
)