package com.herdal.moviehouse.common.data_source

import androidx.paging.PagingData
import com.herdal.moviehouse.data.remote.model.movie_credits.MovieCreditsResponse
import com.herdal.moviehouse.data.remote.model.movie_detail.MovieDetailDto
import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import kotlinx.coroutines.flow.Flow

interface MovieDataSource {
    interface Remote {
        fun getPopularMovies(): Flow<PagingData<MovieDto>>
        fun getTopRatedMovies(): Flow<PagingData<MovieDto>>
        fun getUpcomingMovies(): Flow<PagingData<MovieDto>>
        fun getNowPlayingMovies(): Flow<PagingData<MovieDto>>
        suspend fun getMovieDetails(id: Int): MovieDetailDto
        fun getSimilarMovies(id: Int): Flow<PagingData<MovieDto>>
        fun getRecommendedMovies(id: Int): Flow<PagingData<MovieDto>>
        suspend fun getMovieCredits(movieId: Int): MovieCreditsResponse
        fun getMoviesByGenre(genreId: Int): Flow<PagingData<MovieDto>>
        fun searchMovies(query: String): Flow<PagingData<MovieDto>>
    }
}