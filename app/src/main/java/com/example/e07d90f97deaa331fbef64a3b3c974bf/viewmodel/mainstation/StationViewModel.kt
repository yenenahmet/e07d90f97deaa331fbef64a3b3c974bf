package com.example.e07d90f97deaa331fbef64a3b3c974bf.viewmodel.mainstation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.e07d90f97deaa331fbef64a3b3c974bf.adater.StationAdapter
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.LocalSpaceRepositoryImpl
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.Spacecraft
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.WorldFavorite
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.NetworkSpaceRepositoryImpl
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.entities.World
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.network.mapper.Mappers
import com.yenen.ahmet.basecorelibrary.base.livedata.SingleLiveEvent
import com.yenen.ahmet.basecorelibrary.base.model.LiveDataResponseModel
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants
import com.yenen.ahmet.basecorelibrary.base.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class StationViewModel @Inject constructor(
    private val localSpaceRepositoryImpl: LocalSpaceRepositoryImpl,
    private val networkSpaceRepositoryImpl: NetworkSpaceRepositoryImpl
) : BaseViewModel() {

    val adapter = StationAdapter(0)

    private var liveDataWorldResult =  MutableLiveData<LiveDataResponseModel<Boolean>>()
    private val liveDataAddWorldResult =SingleLiveEvent<LiveDataResponseModel<Boolean>>()

    fun getWorldLiveData():LiveData<LiveDataResponseModel<Boolean>>{
        return liveDataWorldResult
    }

    fun getWorld(){
        viewModelScope.launch {
            try {
                val items =  networkSpaceRepositoryImpl.getWorld()
                localSpaceRepositoryImpl.addAllWorldFavorite(Mappers.getWorldFavorites(items))
                liveDataWorldResult.value = LiveDataResponseModel(true,ProjectConstants.SUCCESS_STATUS,"")
            }catch (ex:Exception){
                liveDataWorldResult.value = LiveDataResponseModel(true,ProjectConstants.ERROR_STATUS,"")
            }
        }
    }

    fun addWorldFavorite(worldFavorite: WorldFavorite){
        viewModelScope.launch {
            try{
                val result = localSpaceRepositoryImpl.addWorldFavorite(worldFavorite)
                liveDataAddWorldResult.value = LiveDataResponseModel(true,ProjectConstants.SUCCESS_STATUS,"")
            }catch (ex:Exception){
                liveDataAddWorldResult.value = LiveDataResponseModel(false,ProjectConstants.ERROR_STATUS,ex.toString())
            }
        }
    }

    fun getWorldLocal():LiveData<List<WorldFavorite>>{
        return localSpaceRepositoryImpl.getWorldFavorites()
    }


    fun getSpacecrafts():LiveData<List<Spacecraft>>{
        return localSpaceRepositoryImpl.getSpacecraft()
    }

    fun updateSpacecraft(spacecraft: Spacecraft){
        viewModelScope.launch {
            try{
                localSpaceRepositoryImpl.addSpacecraft(spacecraft)
            }catch (ex:Exception){
            }
        }
    }

}