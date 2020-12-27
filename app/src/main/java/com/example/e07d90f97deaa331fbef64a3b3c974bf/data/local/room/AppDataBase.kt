package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.Spacecraft
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.WorldFavorite

@Database(
    entities = [Spacecraft::class, WorldFavorite::class],
    version = 8,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getSpacecraftDao(): SpacecraftDao

    abstract fun getWorldFavoriteDao(): WorldFavoriteDao

}