package com.herdal.moviehouse.data.repository

import com.herdal.moviehouse.common.data_source.GenreDataSource
import com.herdal.moviehouse.common.mapper.GenreMapper
import com.herdal.moviehouse.data.remote.model.genre.GenreDto
import com.herdal.moviehouse.domain.repository.GenreRepository
import com.herdal.moviehouse.domain.uimodel.GenreUiModel
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val genreMapper: GenreMapper<GenreDto, GenreUiModel>,
    private val remote: GenreDataSource.Remote
) : GenreRepository {
    override suspend fun getGenres(): List<GenreUiModel> {
        return genreMapper.toDomainList(remote.getGenres().genres)
    }
}