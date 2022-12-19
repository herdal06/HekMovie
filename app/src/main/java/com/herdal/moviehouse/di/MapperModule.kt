package com.herdal.moviehouse.di

import com.herdal.moviehouse.common.mapper.GenreMapper
import com.herdal.moviehouse.common.mapper.GenreMapperImpl
import com.herdal.moviehouse.common.mapper.MovieMapper
import com.herdal.moviehouse.common.mapper.MovieMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object MapperModule {

    @Provides
    @Singleton
    fun provideGenreMapper(
    ): GenreMapper = GenreMapperImpl()

    @Provides
    @Singleton
    fun provideMovieMapper(
    ): MovieMapper = MovieMapperImpl()
}