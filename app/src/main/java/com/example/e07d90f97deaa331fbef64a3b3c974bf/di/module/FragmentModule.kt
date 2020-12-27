package com.example.e07d90f97deaa331fbef64a3b3c974bf.di.module

import com.example.e07d90f97deaa331fbef64a3b3c974bf.view.mainstation.FavoriteFragment
import com.example.e07d90f97deaa331fbef64a3b3c974bf.view.mainstation.StationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeStationFragment(): StationFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment

}