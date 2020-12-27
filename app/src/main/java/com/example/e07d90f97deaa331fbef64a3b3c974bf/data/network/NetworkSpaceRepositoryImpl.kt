package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network

import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.api.SpaceService
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.entities.World

class NetworkSpaceRepositoryImpl (private val spaceService: SpaceService): NetworkSpaceRepository {
    override suspend fun getWorld(): List<World>{
        return spaceService.getWorld()
    }
}