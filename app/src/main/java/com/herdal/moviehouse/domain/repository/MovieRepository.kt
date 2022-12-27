package com.herdal.moviehouse.domain.repository

import androidx.paging.PagingData
import com.herdal.moviehouse.data.remote.model.movie_credits.MovieCreditsResponse
import com.herdal.moviehouse.domain.uimodel.MovieDetailUiModel
import com.herdal.moviehouse.domain.uimodel.MovieUiModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<MovieUiModel>>
    fun getTopRatedMovies(): Flow<PagingData<MovieUiModel>>
    fun getUpcomingMovies(): Flow<PagingData<MovieUiModel>>
    fun getNowPlayingMovies(): Flow<PagingData<MovieUiModel>>
    suspend fun getMovieDetails(id: Int): MovieDetailUiModel
    fun getSimilarMovies(movieId: Int): Flow<PagingData<MovieUiModel>>
    fun getRecommendedMovies(movieId: Int): Flow<PagingData<MovieUiModel>>
    suspend fun getMovieCredits(movieId: Int): MovieCreditsResponse
}