package com.herdal.moviehouse.di

import com.herdal.moviehouse.data.repository.GenreRepositoryImpl
import com.herdal.moviehouse.domain.repository.GenreRepository
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
}