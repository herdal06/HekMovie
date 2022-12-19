package com.herdal.moviehouse.common.data_source

import androidx.paging.PagingData
import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    interface Remote {
        suspend fun getPopularMovies(): Flow<PagingData<MovieDto>>
        suspend fun getTopRatedMovies(): Flow<PagingData<MovieDto>>
        suspend fun getUpcomingMovies(): Flow<PagingData<MovieDto>>
        suspend fun getNowPlayingMovies(): Flow<PagingData<MovieDto>>
    }
}