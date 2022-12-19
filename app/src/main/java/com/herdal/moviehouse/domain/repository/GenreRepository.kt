package com.herdal.moviehouse.domain.repository

import com.herdal.moviehouse.domain.uimodel.GenreUiModel

interface GenreRepository {
    suspend fun getGenres(): List<GenreUiModel>
}