package com.herdal.moviehouse.data.remote.model.person

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KnownForDto(
    @Json(name ="backdrop_path")
    val backdrop_path: String?,
    @Json(name ="first_air_date")
    val first_air_date: String,
    @Json(name ="genre_ids")
    val genre_ids: List<Int>,
    @Json(name ="id")
    val id: Int,
    @Json(name ="media_type")
    val media_type: String,
    @Json(name ="name")
    val name: String,
    @Json(name ="origin_country")
    val origin_country: List<String>,
    @Json(name ="original_language")
    val original_language: String,
    @Json(name ="original_name")
    val original_name: String,
    @Json(name ="original_title")
    val original_title: String,
    @Json(name ="overview")
    val overview: String,
    @Json(name ="poster_path")
    val poster_path: String?,
    @Json(name ="release_date")
    val release_date: String,
    @Json(name ="title")
    val title: String,
    @Json(name ="video")
    val video: Boolean,
    @Json(name ="vote_average")
    val vote_average: Double,
    @Json(name ="vote_count")
    val vote_count: Int
)