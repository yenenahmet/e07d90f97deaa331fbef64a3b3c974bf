package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.Spacecraft
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.WorldFavorite

@Dao
interface WorldFavoriteDao {

    @Query("SELECT * FROM WorldFavoriteEntity WHERE name!='Dünya' ")
    fun getWorldFavorites(): LiveData<List<WorldFavorite>>

    @Query("SELECT * FROM WorldFavoriteEntity WHERE isFavorite = 1 AND name!='Dünya'")
    fun getWorldFavoritesTrue(): LiveData<List<WorldFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllWorldFavorite(worldFavorites: List<WorldFavorite>):List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWorldFavorite(worldFavorites: WorldFavorite):Long

    @Query("DELETE FROM WorldFavoriteEntity")
    suspend fun deleteWorldFavorites()

}