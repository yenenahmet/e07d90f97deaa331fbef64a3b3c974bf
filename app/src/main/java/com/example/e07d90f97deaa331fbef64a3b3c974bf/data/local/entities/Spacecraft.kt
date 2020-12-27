package com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "SpacecraftEntity")
data class Spacecraft(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "durability")
    var durability: Int,
    @ColumnInfo(name = "speed")
    var speed: Int ,
    @ColumnInfo(name = "capacity")
    var capacity: Int,
    @ColumnInfo(name = "lastCoordinateX")
    var lastCoordinateX: Int,
    @ColumnInfo(name = "lastCoordinateY")
    var lastCoordinateY: Int,
    @ColumnInfo(name = "damage")
    var damage: Int,
    @PrimaryKey
    var SpacecraftId: String,
)
