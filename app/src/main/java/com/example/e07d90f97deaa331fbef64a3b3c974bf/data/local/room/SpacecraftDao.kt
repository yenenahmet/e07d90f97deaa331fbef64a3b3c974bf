package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.Spacecraft

@Dao
interface SpacecraftDao {

    @Query("SELECT * FROM SpacecraftEntity")
    fun getSpacecrafts(): LiveData<List<Spacecraft>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSpacecraft(spacecraft:Spacecraft):Long


}