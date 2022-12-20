package com.herdal.moviehouse.di

import com.herdal.moviehouse.common.mapper.*
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

    @Provides
    @Singleton
    fun provideCompanyMapper(
    ): CompanyMapper = CompanyMapperImpl()

    @Provides
    @Singleton
    fun provideMovieDetailMapper(
    ): MovieDetailMapper = MovieDetailMapperImpl(
        provideGenreMapper(),
        provideCompanyMapper()
    )
}