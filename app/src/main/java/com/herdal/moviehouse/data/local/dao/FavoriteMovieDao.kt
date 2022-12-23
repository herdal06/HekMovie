package com.herdal.moviehouse.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.herdal.moviehouse.data.local.entity.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM movies")
    fun getAll(): Flow<List<FavoriteMovieEntity>>

    @Insert
    fun insert(favoriteMovieEntity: FavoriteMovieEntity)

    @Delete
    fun delete(favoriteMovieEntity: FavoriteMovieEntity)
}