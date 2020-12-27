package com.example.e07d90f97deaa331fbef64a3b3c974bf.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e07d90f97deaa331fbef64a3b3c974bf.di.scope.ViewModelKey
import com.example.e07d90f97deaa331fbef64a3b3c974bf.viewmodel.main.MainViewModel
import com.example.e07d90f97deaa331fbef64a3b3c974bf.viewmodel.mainstation.FavoriteViewModel
import com.example.e07d90f97deaa331fbef64a3b3c974bf.viewmodel.mainstation.MainStationViewModel
import com.example.e07d90f97deaa331fbef64a3b3c974bf.viewmodel.mainstation.StationViewModel
import com.yenen.ahmet.basecorelibrary.base.di.factory.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainStationViewModel::class)
    internal abstract fun bindMainStationViewModel(viewModel: MainStationViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(StationViewModel::class)
    internal abstract fun bindStationViewModel(viewModel: StationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    internal abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}