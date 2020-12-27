package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "WorldFavoriteEntity",
    indices = [Index(value = ["name"], unique = true)]
)
data class WorldFavorite(
    var name: String,
    var coordinateX: Int,
    var coordinateY: Int,
    var capacity: Int,
    var stock: Int,
    var need: Int,
    var isFavorite: Boolean,
    var isItDone: Boolean,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var worldId: Long? = null
)
