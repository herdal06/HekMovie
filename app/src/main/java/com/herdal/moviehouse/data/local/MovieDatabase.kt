package com.herdal.moviehouse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.herdal.moviehouse.data.local.dao.FavoriteMovieDao
import com.herdal.moviehouse.data.local.entity.FavoriteMovieEntity

@Database(
    entities = [FavoriteMovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(IntegerListTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): FavoriteMovieDao
}