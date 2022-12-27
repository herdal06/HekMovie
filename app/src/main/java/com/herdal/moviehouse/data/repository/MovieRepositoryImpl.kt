package com.herdal.moviehouse.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.herdal.moviehouse.common.data_source.MovieDataSource
import com.herdal.moviehouse.common.mapper.movie.MovieDetailMapper
import com.herdal.moviehouse.common.mapper.movie.MovieMapper
import com.herdal.moviehouse.common.mapper.movie_credits.MovieCreditsMapper
import com.herdal.moviehouse.data.remote.model.movie_credits.MovieCreditsResponse
import com.herdal.moviehouse.domain.repository.MovieRepository
import com.herdal.moviehouse.domain.uimodel.MovieDetailUiModel
import com.herdal.moviehouse.domain.uimodel.MovieUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieMapper: MovieMapper,
    private val remote: MovieDataSource.Remote,
    private val movieDetailMapper: MovieDetailMapper,
    private val movieCreditsMapper: MovieCreditsMapper
) : MovieRepository {
    override fun getPopularMovies(): Flow<PagingData<MovieUiModel>> {
        val domainPopularMovie = remote.getPopularMovies().map { pagingData ->
            pagingData.map { domainPopularMovie ->
                movieMapper.toDomain(domainPopularMovie)
            }
        }
        Timber.d("popular: $domainPopularMovie")
        return domainPopularMovie
    }

    override fun getTopRatedMovies(): Flow<PagingData<MovieUiModel>> {
        val domainTopRatedMovie = remote.getTopRatedMovies().map { pagingData ->
            pagingData.map { domainTopRatedMovie ->
                movieMapper.toDomain(domainTopRatedMovie)
            }
        }
        Timber.d("top rated: $domainTopRatedMovie")
        return domainTopRatedMovie
    }

    override fun getUpcomingMovies(): Flow<PagingData<MovieUiModel>> {
        val domainUpcomingMovie = remote.getUpcomingMovies().map { pagingData ->
            pagingData.map { domainUpcomingMovie ->
                movieMapper.toDomain(domainUpcomingMovie)
            }
        }
        Timber.d("upcoming: $domainUpcomingMovie")
        return domainUpcomingMovie
    }

    override fun getNowPlayingMovies(): Flow<PagingData<MovieUiModel>> {
        val domainNowPlayingMovie = remote.getNowPlayingMovies().map { pagingData ->
            pagingData.map { domainNowPlayingMovie ->
                movieMapper.toDomain(domainNowPlayingMovie)
            }
        }
        Timber.d("now playing: $domainNowPlayingMovie")
        return domainNowPlayingMovie
    }

    override suspend fun getMovieDetails(id: Int): MovieDetailUiModel {
        val movieDetailDto = remote.getMovieDetails(id)
        return movieDetailMapper.toDomain(movieDetailDto)
    }

    override fun getSimilarMovies(movieId: Int): Flow<PagingData<MovieUiModel>> {
        val domainSimilarMovie = remote.getSimilarMovies(movieId).map { pagingData ->
            pagingData.map { domainSimilarMovie ->
                movieMapper.toDomain(domainSimilarMovie)
            }
        }
        Timber.d("popular: $domainSimilarMovie")
        return domainSimilarMovie
    }

    override fun getRecommendedMovies(movieId: Int): Flow<PagingData<MovieUiModel>> {
        val domainRecommendedMovie = remote.getRecommendedMovies(movieId).map { pagingData ->
            pagingData.map { domainRecommendedMovie ->
                movieMapper.toDomain(domainRecommendedMovie)
            }
        }
        Timber.d("popular: $domainRecommendedMovie")
        return domainRecommendedMovie
    }

    override suspend fun getMovieCredits(movieId: Int): MovieCreditsResponse {
        return remote.getMovieCredits(movieId = movieId)
    }

    override fun getMoviesByGenre(genreId: Int): Flow<PagingData<MovieUiModel>> {
        val domainMovies = remote.getMoviesByGenre(genreId).map { pagingData ->
            pagingData.map { domainTopRatedMovie ->
                movieMapper.toDomain(domainTopRatedMovie)
            }
        }
        Timber.d("top rated: $domainMovies")
        return domainMovies
    }
}