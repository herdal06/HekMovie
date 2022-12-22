package com.herdal.moviehouse.di

import com.herdal.moviehouse.common.mapper.*
import com.herdal.moviehouse.common.mapper.person.KnownForMapper
import com.herdal.moviehouse.common.mapper.person.PersonMapper
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

    @Provides
    @Singleton
    fun provideAuthorDetailMapper(
    ): AuthorMapper = AuthorMapperImpl()

    @Provides
    @Singleton
    fun provideReviewMapper(
    ): ReviewMapper = ReviewMapperImpl(
        provideAuthorDetailMapper()
    )

    @Provides
    @Singleton
    fun provideKnownForMapper(
    ): KnownForMapper = KnownForMapper()

    @Provides
    @Singleton
    fun providePersonMapper(
    ): PersonMapper = PersonMapper(provideKnownForMapper())
}