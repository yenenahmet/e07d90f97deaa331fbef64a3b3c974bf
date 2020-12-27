package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.mapper

import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.WorldFavorite
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.entities.World

object Mappers {

    private fun getWorldFavorite(world: World): WorldFavorite {
        return WorldFavorite(
            world.name,
            world.coordinateX,
            world.coordinateY,
            world.capacity,
            world.stock,
            world.need,
            false,
            false
        )
    }

    fun getWorldFavorites(items:List<World>):List<WorldFavorite>{
        val worldFavorites = mutableListOf<WorldFavorite>()
        items.forEach {
            worldFavorites.add(getWorldFavorite(it))
        }
        return worldFavorites
    }
}