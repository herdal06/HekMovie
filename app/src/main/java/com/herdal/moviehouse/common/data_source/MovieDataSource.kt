package com.herdal.moviehouse.common.data_source

import androidx.paging.PagingData
import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    interface Remote {
        suspend fun getPopularMovies(page: Int): Flow<PagingData<MovieDto>>
        suspend fun getTopRatedMovies(page: Int): Flow<PagingData<MovieDto>>
        suspend fun getUpcomingMovies(page: Int): Flow<PagingData<MovieDto>>
        suspend fun getNowPlayingMovies(page: Int): Flow<PagingData<MovieDto>>
    }
}