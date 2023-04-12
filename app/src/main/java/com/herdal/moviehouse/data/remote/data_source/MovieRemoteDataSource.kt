package com.herdal.moviehouse.data.remote.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.herdal.moviehouse.data.data_source.MovieDataSource
import com.herdal.moviehouse.common.enums.MovieListType
import com.herdal.moviehouse.data.remote.model.movie_credits.MovieCreditsResponse
import com.herdal.moviehouse.data.remote.model.movie_detail.MovieDetailDto
import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import com.herdal.moviehouse.data.remote.paging_source.MoviePagingSource
import com.herdal.moviehouse.data.remote.service.MovieService
import com.herdal.moviehouse.utils.ApiConstants.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieService: MovieService,
) : MovieDataSource.Remote {

    private fun createMoviePager(pagingSource: PagingSource<Int, MovieDto>): Flow<PagingData<MovieDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    override fun getPopularMovies(): Flow<PagingData<MovieDto>> =
        createMoviePager(MoviePagingSource(movieService, MovieListType.POPULAR))

    override fun getTopRatedMovies(): Flow<PagingData<MovieDto>> =
        createMoviePager(MoviePagingSource(movieService, MovieListType.TOP_RATED))

    override fun getUpcomingMovies(): Flow<PagingData<MovieDto>> =
        createMoviePager(MoviePagingSource(movieService, MovieListType.UPCOMING))

    override fun getNowPlayingMovies(): Flow<PagingData<MovieDto>> =
        createMoviePager(MoviePagingSource(movieService, MovieListType.NOW_PLAYING))

    override fun getSimilarMovies(id: Int): Flow<PagingData<MovieDto>> =
        createMoviePager(MoviePagingSource(movieService, MovieListType.SIMILAR, movieId = id))

    override fun getRecommendedMovies(id: Int): Flow<PagingData<MovieDto>> =
        createMoviePager(MoviePagingSource(movieService, MovieListType.POPULAR, movieId = id))

    override suspend fun getMovieCredits(movieId: Int): MovieCreditsResponse =
        movieService.getMovieCredits(movieId = movieId)

    override fun getMoviesByGenre(genreId: Int): Flow<PagingData<MovieDto>> =
        createMoviePager(MoviePagingSource(movieService, MovieListType.POPULAR, genreId = genreId))

    override suspend fun getMovieDetails(id: Int): MovieDetailDto =
        movieService.getMovieDetails(id)
}