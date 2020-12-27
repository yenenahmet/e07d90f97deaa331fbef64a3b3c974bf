package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.api

import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.entities.World
import retrofit2.http.GET

interface SpaceService {

    @GET("e7211664-cbb6-4357-9c9d-f12bf8bab2e2")
    suspend fun getWorld(): List<World>
}