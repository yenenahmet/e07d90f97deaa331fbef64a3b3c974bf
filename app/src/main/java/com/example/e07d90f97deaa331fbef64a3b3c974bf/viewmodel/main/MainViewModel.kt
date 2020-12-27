package com.example.e07d90f97deaa331fbef64a3b3c974bf.viewmodel.main

import androidx.lifecycle.viewModelScope
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.LocalSpaceRepositoryImpl
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.Spacecraft
import com.yenen.ahmet.basecorelibrary.base.livedata.SingleLiveEvent
import com.yenen.ahmet.basecorelibrary.base.model.LiveDataResponseModel
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants
import com.yenen.ahmet.basecorelibrary.base.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val localSpaceRepositoryImpl: LocalSpaceRepositoryImpl):BaseViewModel() {

    private val liveDataAddSpacecraft = SingleLiveEvent<LiveDataResponseModel<Long>>()

    fun getLiveDataAddSpacecraft():SingleLiveEvent<LiveDataResponseModel<Long>>{
        return liveDataAddSpacecraft
    }

    fun addSpacecraft(spacecraft: Spacecraft){
        viewModelScope.launch {
            try{
                val result = localSpaceRepositoryImpl.addSpacecraft(spacecraft)
                liveDataAddSpacecraft.value = LiveDataResponseModel(result,ProjectConstants.SUCCESS_STATUS,"")
            }catch (ex:Exception){
                liveDataAddSpacecraft.value =  LiveDataResponseModel(null,ProjectConstants.ERROR_STATUS,ex.toString())
            }
        }
    }

    fun clear(){
        viewModelScope.launch {
            try{
                localSpaceRepositoryImpl.deleteWorldFavorites()
            }catch (ex:Exception){
            }
        }
    }


}