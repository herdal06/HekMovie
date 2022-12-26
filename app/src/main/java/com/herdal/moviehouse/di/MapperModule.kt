package com.herdal.moviehouse.di

import com.herdal.moviehouse.common.mapper.movie.MovieDetailMapper
import com.herdal.moviehouse.common.mapper.movie.CompanyMapper
import com.herdal.moviehouse.common.mapper.movie.GenreMapper
import com.herdal.moviehouse.common.mapper.movie.MovieMapper
import com.herdal.moviehouse.common.mapper.person.KnownForMapper
import com.herdal.moviehouse.common.mapper.person.PersonDetailMapper
import com.herdal.moviehouse.common.mapper.person.PersonMapper
import com.herdal.moviehouse.common.mapper.review.AuthorMapper
import com.herdal.moviehouse.common.mapper.review.ReviewMapper
import com.herdal.moviehouse.common.mapper.watch_providers.BuyMapper
import com.herdal.moviehouse.common.mapper.watch_providers.USMapper
import com.herdal.moviehouse.common.mapper.watch_providers.WatchProvidersMapper
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
    ): GenreMapper = GenreMapper()

    @Provides
    @Singleton
    fun provideMovieMapper(
    ): MovieMapper = MovieMapper()

    @Provides
    @Singleton
    fun provideCompanyMapper(
    ): CompanyMapper = CompanyMapper()

    @Provides
    @Singleton
    fun provideMovieDetailMapper(
    ): MovieDetailMapper = MovieDetailMapper(
        provideGenreMapper(),
        provideCompanyMapper()
    )

    @Provides
    @Singleton
    fun provideAuthorDetailMapper(
    ): AuthorMapper = AuthorMapper()

    @Provides
    @Singleton
    fun provideReviewMapper(
    ): ReviewMapper = ReviewMapper(
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

    @Provides
    @Singleton
    fun providePersonDetailMapper(
    ): PersonDetailMapper = PersonDetailMapper()

    @Provides
    @Singleton
    fun provideBuyMapper(
    ): BuyMapper = BuyMapper()

    @Provides
    @Singleton
    fun provideUSMapper(
    ): USMapper = USMapper(
        provideBuyMapper()
    )

    @Provides
    @Singleton
    fun provideWatchProvidersMapper(
    ): WatchProvidersMapper = WatchProvidersMapper(
        provideUSMapper()
    )
}