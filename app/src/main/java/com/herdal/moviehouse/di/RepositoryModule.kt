package com.herdal.moviehouse.di

import com.herdal.moviehouse.data.repository.GenreRepositoryImpl
import com.herdal.moviehouse.data.repository.MovieRepositoryImpl
import com.herdal.moviehouse.data.repository.PersonRepositoryImpl
import com.herdal.moviehouse.data.repository.ReviewRepositoryImpl
import com.herdal.moviehouse.domain.repository.GenreRepository
import com.herdal.moviehouse.domain.repository.MovieRepository
import com.herdal.moviehouse.domain.repository.PersonRepository
import com.herdal.moviehouse.domain.repository.ReviewRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class RepositoryModule {

    @Binds
    abstract fun bindGenreRepository(
        genreRepositoryImpl: GenreRepositoryImpl
    ): GenreRepository

    @Binds
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    abstract fun bindReviewRepository(
        reviewRepositoryImpl: ReviewRepositoryImpl
    ): ReviewRepository

    @Binds
    abstract fun binPersonRepository(
        personRepositoryImpl: PersonRepositoryImpl
    ): PersonRepository
}