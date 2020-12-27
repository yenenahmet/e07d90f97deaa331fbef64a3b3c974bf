package com.example.e07d90f97deaa331fbef64a3b3c974bf.di.module

import android.app.Application
import android.content.Context
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.LocalSpaceRepositoryImpl
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.room.SpacecraftDao
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.room.WorldFavoriteDao
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.NetworkSpaceRepositoryImpl
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.api.SpaceService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideNetworkSpaceRepository(service: SpaceService): NetworkSpaceRepositoryImpl {
        return NetworkSpaceRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideLocalSpaceRepository(spacecraftDao: SpacecraftDao,worldFavoriteDao: WorldFavoriteDao): LocalSpaceRepositoryImpl {
        return LocalSpaceRepositoryImpl(spacecraftDao,worldFavoriteDao)
    }

}