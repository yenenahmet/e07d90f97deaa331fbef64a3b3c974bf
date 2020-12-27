package com.example.e07d90f97deaa331fbef64a3b3c974bf

import com.example.e07d90f97deaa331fbef64a3b3c974bf.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }


    private val appComponent = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

}