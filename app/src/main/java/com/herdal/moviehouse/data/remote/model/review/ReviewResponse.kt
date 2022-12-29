package com.herdal.moviehouse.data.remote.model.review

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResponse(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<ReviewDto>,
    @Json(name = "total_pages")
    val total_pages: Int?,
    @Json(name = "total_results")
    val total_results: Int?
)