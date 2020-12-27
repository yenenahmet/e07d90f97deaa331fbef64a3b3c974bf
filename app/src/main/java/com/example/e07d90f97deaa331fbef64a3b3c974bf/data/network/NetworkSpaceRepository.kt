package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network

import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.entities.World

interface NetworkSpaceRepository {

    suspend fun getWorld(): List<World>
}