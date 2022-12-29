package com.herdal.moviehouse.data.remote.model.person

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonResponse(
    @Json(name ="page")
    val page: Int?,
    @Json(name ="results")
    val results: List<PersonDto>,
    @Json(name ="total_pages")
    val total_pages: Int?,
    @Json(name ="total_results")
    val total_results: Int?
)