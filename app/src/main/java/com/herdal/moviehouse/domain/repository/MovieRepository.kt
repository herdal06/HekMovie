package com.herdal.moviehouse.domain.repository

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.uimodel.MovieUiModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Flow<PagingData<MovieUiModel>>
    suspend fun getTopRatedMovies(): Flow<PagingData<MovieUiModel>>
    suspend fun getUpcomingMovies(): Flow<PagingData<MovieUiModel>>
    suspend fun getNowPlayingMovies(): Flow<PagingData<MovieUiModel>>
}