package com.herdal.moviehouse.common.data_source

import com.herdal.moviehouse.data.remote.model.genre.GenreResponse

interface GenreDataSource {
    interface Remote {
        suspend fun getGenres(): GenreResponse
    }
}