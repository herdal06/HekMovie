package com.herdal.moviehouse.data.remote.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.herdal.moviehouse.common.data_source.MovieDataSource
import com.herdal.moviehouse.common.enums.MoviesEnum
import com.herdal.moviehouse.common.enums.MovieDetailEnum
import com.herdal.moviehouse.data.remote.model.movie_detail.MovieDetailDto
import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import com.herdal.moviehouse.data.remote.paging_source.MoviePagingSource
import com.herdal.moviehouse.data.remote.paging_source.MovieDetailPagingSource
import com.herdal.moviehouse.data.remote.service.MovieService
import com.herdal.moviehouse.utils.ApiConstants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService,
) : MovieDataSource.Remote {
    override fun getPopularMovies(): Flow<PagingData<MovieDto>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { MoviePagingSource(movieService, MoviesEnum.POPULAR_MOVIES) }
        ).flow

    override fun getTopRatedMovies(): Flow<PagingData<MovieDto>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { MoviePagingSource(movieService, MoviesEnum.TOP_RATED_MOVIES) }
        ).flow

    override fun getUpcomingMovies(): Flow<PagingData<MovieDto>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { MoviePagingSource(movieService, MoviesEnum.UPCOMING) }
        ).flow

    override fun getNowPlayingMovies(): Flow<PagingData<MovieDto>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { MoviePagingSource(movieService, MoviesEnum.NOW_PLAYING_MOVIES) }
        ).flow

    override suspend fun getMovieDetails(id: Int): MovieDetailDto =
        movieService.getMovieDetails(id)

    override fun getSimilarMovies(id: Int): Flow<PagingData<MovieDto>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                MovieDetailPagingSource(
                    movieService,
                    MovieDetailEnum.SIMILAR,
                    id
                )
            }
        ).flow

    override fun getRecommendedMovies(id: Int): Flow<PagingData<MovieDto>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                MovieDetailPagingSource(
                    movieService,
                    MovieDetailEnum.RECOMMENDATIONS,
                    id
                )
            }
        ).flow
}