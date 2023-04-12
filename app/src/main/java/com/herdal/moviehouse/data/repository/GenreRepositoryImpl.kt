package com.herdal.moviehouse.data.repository

import com.herdal.moviehouse.data.data_source.GenreDataSource
import com.herdal.moviehouse.common.mapper.movie.GenreMapper
import com.herdal.moviehouse.domain.repository.GenreRepository
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val genreMapper: GenreMapper,
    private val remote: GenreDataSource.Remote
) : GenreRepository {
    override suspend fun getGenres(): List<GenreUiModel> {
        return genreMapper.toDomainList(remote.getGenres().genres)
    }
}