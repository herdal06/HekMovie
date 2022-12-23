package com.herdal.moviehouse.di

import android.content.Context
import androidx.room.Room
import com.herdal.moviehouse.data.local.MovieDatabase
import com.herdal.moviehouse.data.local.dao.FavoriteMovieDao
import com.herdal.moviehouse.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        Constants.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideMovieDao(
        db: MovieDatabase
    ): FavoriteMovieDao = db.movieDao()
}