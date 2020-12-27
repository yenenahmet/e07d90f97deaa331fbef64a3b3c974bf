package com.example.e07d90f97deaa331fbef64a3b3c974bf.di.module

import com.example.e07d90f97deaa331fbef64a3b3c974bf.view.MainActivity
import com.example.e07d90f97deaa331fbef64a3b3c974bf.view.mainstation.MainStationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity


    @ContributesAndroidInjector
    internal abstract fun contributeMainStationActivity(): MainStationActivity
}