package com.herdal.moviehouse.data.remote.data_source

import com.herdal.moviehouse.common.data_source.GenreDataSource
import com.herdal.moviehouse.data.remote.model.genre.GenreResponse
import com.herdal.moviehouse.data.remote.service.GenreService
import javax.inject.Inject

class GenreRemoteDataSource @Inject constructor(
    private val genreService: GenreService
) : GenreDataSource.Remote {
    override suspend fun getGenres(): GenreResponse = genreService.getAll()
}