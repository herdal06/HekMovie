package com.herdal.moviehouse.di

import com.herdal.moviehouse.common.data_source.GenreDataSource
import com.herdal.moviehouse.data.remote.data_source.GenreRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class DataSourceModule {

    @Binds
    abstract fun bindGenreRemoteDataSource(genreRemoteDataSource: GenreRemoteDataSource): GenreDataSource.Remote
}