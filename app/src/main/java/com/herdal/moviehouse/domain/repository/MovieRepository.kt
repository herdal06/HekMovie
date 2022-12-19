package com.herdal.moviehouse.domain.repository

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.uimodel.MovieUiModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<MovieUiModel>>
    fun getTopRatedMovies(): Flow<PagingData<MovieUiModel>>
    fun getUpcomingMovies(): Flow<PagingData<MovieUiModel>>
    fun getNowPlayingMovies(): Flow<PagingData<MovieUiModel>>
}