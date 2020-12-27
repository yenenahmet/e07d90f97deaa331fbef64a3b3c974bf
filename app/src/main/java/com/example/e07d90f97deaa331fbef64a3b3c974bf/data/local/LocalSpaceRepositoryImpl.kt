package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local

import androidx.lifecycle.LiveData
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.Spacecraft
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.WorldFavorite
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.room.SpacecraftDao
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.room.WorldFavoriteDao

class LocalSpaceRepositoryImpl (private val spacecraftDao: SpacecraftDao,
                                private val worldFavoriteDao: WorldFavoriteDao):LocalSpaceRepository {

    override suspend fun addSpacecraft(spacecraft: Spacecraft): Long {
       return spacecraftDao.addSpacecraft(spacecraft)
    }

    override fun getSpacecraft(): LiveData<List<Spacecraft>> {
        return spacecraftDao.getSpacecrafts()
    }

    override fun getWorldFavorites(): LiveData<List<WorldFavorite>> {
        return worldFavoriteDao.getWorldFavorites()
    }

    override suspend fun addAllWorldFavorite(worldFavorites: List<WorldFavorite>): List<Long> {
       return worldFavoriteDao.addAllWorldFavorite(worldFavorites)
    }

    override fun getWorldFavoritesTrue(): LiveData<List<WorldFavorite>> {
        return worldFavoriteDao.getWorldFavoritesTrue()
    }

    override suspend fun addWorldFavorite(worldFavorite: WorldFavorite): Long {
        return worldFavoriteDao.addWorldFavorite(worldFavorite)
    }

    override suspend fun deleteWorldFavorites() {
        worldFavoriteDao.deleteWorldFavorites()
    }
}