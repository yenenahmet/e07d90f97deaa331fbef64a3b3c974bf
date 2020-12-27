package com.example.e07d90f97deaa331fbef64a3b3c974bf.di.module

import android.content.Context
import androidx.room.Room
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.Spacecraft
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.room.AppDataBase
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.room.SpacecraftDao
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.room.WorldFavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    @Singleton
    fun provideAppDataBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "Space")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideSpacecraftDao(db: AppDataBase): SpacecraftDao {
        return db.getSpacecraftDao()
    }


    @Provides
    @Singleton
    fun provideWorldFavoriteDao(db: AppDataBase): WorldFavoriteDao {
        return db.getWorldFavoriteDao()
    }


}