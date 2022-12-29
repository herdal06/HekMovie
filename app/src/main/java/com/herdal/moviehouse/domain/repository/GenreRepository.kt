package com.herdal.moviehouse.domain.repository

import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel

interface GenreRepository {
    suspend fun getGenres(): List<GenreUiModel>
}