package com.herdal.moviehouse.data.remote.model.review

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewDto(
    @Json(name = "author")
    val author: String?,
    @Json(name = "author_details")
    val author_details: AuthorDetailsDto,
    @Json(name = "content")
    val content: String?,
    @Json(name = "created_at")
    val created_at: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "updated_at")
    val updated_at: String?,
    @Json(name = "url")
    val url: String?
)