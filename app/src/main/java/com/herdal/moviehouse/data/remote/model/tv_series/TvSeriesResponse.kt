package com.herdal.moviehouse.data.remote.model.tv_series

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvSeriesResponse(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<TvSeriesDto>,
    @Json(name = "total_pages")
    val total_pages: Int?,
    @Json(name = "total_results")
    val total_results: Int?
)