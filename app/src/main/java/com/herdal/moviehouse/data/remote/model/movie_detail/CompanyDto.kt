package com.herdal.moviehouse.data.remote.model.movie_detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CompanyDto(
    @Json(name ="id")
    val id: Int?,
    @Json(name ="logo_path")
    val logo_path: String?,
    @Json(name ="name")
    val name: String?,
    @Json(name ="origin_country")
    val origin_country: String?
)