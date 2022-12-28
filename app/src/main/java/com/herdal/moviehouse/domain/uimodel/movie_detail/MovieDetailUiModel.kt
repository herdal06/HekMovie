package com.herdal.moviehouse.domain.uimodel.movie_detail

import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel

data class MovieDetailUiModel(
    val adult: Boolean,
    val backdrop_path: String?,
    val budget: Int,
    val genres: List<GenreUiModel>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<CompanyUiModel>,
    val release_date: String,
    val revenue: Double,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)