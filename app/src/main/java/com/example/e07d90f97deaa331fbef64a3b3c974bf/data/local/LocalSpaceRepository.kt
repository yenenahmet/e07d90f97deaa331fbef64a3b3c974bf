package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local

import androidx.lifecycle.LiveData
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.Spacecraft
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.WorldFavorite

interface LocalSpaceRepository {
    suspend fun addSpacecraft(spacecraft: Spacecraft): Long
    fun getSpacecraft():LiveData<List<Spacecraft>>
    fun getWorldFavorites(): LiveData<List<WorldFavorite>>
    suspend fun addAllWorldFavorite(worldFavorites: List<WorldFavorite>): List<Long>
    fun getWorldFavoritesTrue(): LiveData<List<WorldFavorite>>
    suspend fun addWorldFavorite(worldFavorite: WorldFavorite): Long
    suspend fun deleteWorldFavorites()
}