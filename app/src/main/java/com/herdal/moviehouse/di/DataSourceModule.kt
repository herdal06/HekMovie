package com.herdal.moviehouse.di

import com.herdal.moviehouse.common.data_source.GenreDataSource
import com.herdal.moviehouse.common.data_source.MovieDataSource
import com.herdal.moviehouse.common.data_source.ReviewDataSource
import com.herdal.moviehouse.data.remote.data_source.GenreRemoteDataSource
import com.herdal.moviehouse.data.remote.data_source.MovieRemoteDataSource
import com.herdal.moviehouse.data.remote.data_source.ReviewRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class DataSourceModule {

    @Binds
    abstract fun bindGenreRemoteDataSource(genreRemoteDataSource: GenreRemoteDataSource): GenreDataSource.Remote

    @Binds
    abstract fun bindMovieRemoteDataSource(movieRemoteDataSource: MovieRemoteDataSource): MovieDataSource.Remote

    @Binds
    abstract fun bindReviewRemoteDataSource(reviewRemoteDataSource: ReviewRemoteDataSource): ReviewDataSource.Remote
}