package com.example.e07d90f97deaa331fbef64a3b3c974bf.viewmodel.mainstation


import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.e07d90f97deaa331fbef64a3b3c974bf.adater.StationAdapter
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.LocalSpaceRepositoryImpl
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.WorldFavorite
import com.yenen.ahmet.basecorelibrary.base.livedata.SingleLiveEvent
import com.yenen.ahmet.basecorelibrary.base.model.LiveDataResponseModel
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants
import com.yenen.ahmet.basecorelibrary.base.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val localSpaceRepositoryImpl: LocalSpaceRepositoryImpl) :
    BaseViewModel() {

    private val liveDataAddWorldResult = SingleLiveEvent<LiveDataResponseModel<Boolean>>()
    val adapter = StationAdapter(1)

    fun getWorldFavorites(): LiveData<List<WorldFavorite>> {
        return localSpaceRepositoryImpl.getWorldFavoritesTrue()
    }

    fun addWorldFavorite(worldFavorite: WorldFavorite){
        viewModelScope.launch {
            try{
                val result = localSpaceRepositoryImpl.addWorldFavorite(worldFavorite)
                liveDataAddWorldResult.value = LiveDataResponseModel(true,
                    ProjectConstants.SUCCESS_STATUS,"")
            }catch (ex:Exception){
                liveDataAddWorldResult.value = LiveDataResponseModel(false,
                    ProjectConstants.ERROR_STATUS,ex.toString())
            }
        }
    }

}