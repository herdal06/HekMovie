package com.herdal.moviehouse.di

import com.herdal.moviehouse.domain.repository.GenreRepository
import com.herdal.moviehouse.domain.use_case.genre.GetGenresUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetGenresUseCase(genreRepository: GenreRepository) = GetGenresUseCase(
        genreRepository = genreRepository
    )
}