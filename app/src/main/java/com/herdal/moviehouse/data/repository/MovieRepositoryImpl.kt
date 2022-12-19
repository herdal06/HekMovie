package com.herdal.moviehouse.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.herdal.moviehouse.common.data_source.MovieDataSource
import com.herdal.moviehouse.common.mapper.MovieMapper
import com.herdal.moviehouse.domain.repository.MovieRepository
import com.herdal.moviehouse.domain.uimodel.MovieUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieMapper: MovieMapper,
    private val remote: MovieDataSource.Remote
) : MovieRepository {
    override fun getPopularMovies(): Flow<PagingData<MovieUiModel>> {
        val domainProduct = remote.getPopularMovies().map { pagingData ->
            pagingData.map { remoteProduct ->
                movieMapper.toDomain(remoteProduct)
            }
        }
        Timber.d("popular: $domainProduct")
        return domainProduct
    }

    override fun getTopRatedMovies(): Flow<PagingData<MovieUiModel>> {
        val domainProduct = remote.getTopRatedMovies().map { pagingData ->
            pagingData.map { remoteProduct ->
                movieMapper.toDomain(remoteProduct)
            }
        }
        Timber.d("top rated: $domainProduct")
        return domainProduct
    }

    override fun getUpcomingMovies(): Flow<PagingData<MovieUiModel>> {
        val domainProduct = remote.getUpcomingMovies().map { pagingData ->
            pagingData.map { remoteProduct ->
                movieMapper.toDomain(remoteProduct)
            }
        }
        Timber.d("upcoming: $domainProduct")
        return domainProduct
    }

    override fun getNowPlayingMovies(): Flow<PagingData<MovieUiModel>> {
        val domainProduct = remote.getNowPlayingMovies().map { pagingData ->
            pagingData.map { remoteProduct ->
                movieMapper.toDomain(remoteProduct)
            }
        }
        Timber.d("now playing: $domainProduct")
        return domainProduct
    }
}