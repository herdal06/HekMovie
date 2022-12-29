package com.herdal.moviehouse.data.remote.model.movie_detail

import com.herdal.moviehouse.data.remote.model.genre.GenreDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailDto(
    @Json(name ="adult")
    val adult: Boolean?,
    @Json(name ="backdrop_path")
    val backdrop_path: String?,
    @Json(name ="budget")
    val budget: Int?,
    @Json(name ="genres")
    val genres: List<GenreDto>?,
    @Json(name ="homepage")
    val homepage: String?,
    @Json(name ="id")
    val id: Int?,
    @Json(name ="imdb_id")
    val imdb_id: String?,
    @Json(name ="original_language")
    val original_language: String?,
    @Json(name ="original_title")
    val original_title: String?,
    @Json(name ="overview")
    val overview: String?,
    @Json(name ="popularity")
    val popularity: Double?,
    @Json(name ="poster_path")
    val poster_path: String?,
    @Json(name ="production_companies")
    val production_companies: List<CompanyDto>,
    @Json(name ="release_date")
    val release_date: String?,
    @Json(name ="revenue")
    val revenue: Double?,
    @Json(name ="runtime")
    val runtime: Int?,
    @Json(name ="status")
    val status: String?,
    @Json(name ="tagline")
    val tagline: String?,
    @Json(name ="title")
    val title: String?,
    @Json(name ="vote_average")
    val vote_average: Double?,
    @Json(name ="vote_count")
    val vote_count: Int?
)