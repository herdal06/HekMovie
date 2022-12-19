package com.herdal.moviehouse.common.data_source

import androidx.paging.PagingData
import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    interface Remote {
        fun getPopularMovies(): Flow<PagingData<MovieDto>>
        fun getTopRatedMovies(): Flow<PagingData<MovieDto>>
        fun getUpcomingMovies(): Flow<PagingData<MovieDto>>
        fun getNowPlayingMovies(): Flow<PagingData<MovieDto>>
    }
}