package com.example.e07d90f97deaa331fbef64a3b3c974bf.adater

import android.annotation.SuppressLint
import android.view.View
import com.example.e07d90f97deaa331fbef64a3b3c974bf.R
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.WorldFavorite
import com.example.e07d90f97deaa331fbef64a3b3c974bf.databinding.ItemStationBinding
import com.yenen.ahmet.basecorelibrary.base.adapter.BaseViewBindingRecyclerViewFilterAdapter
import java.util.*

class StationAdapter(private val type: Int) :
    BaseViewBindingRecyclerViewFilterAdapter<WorldFavorite, ItemStationBinding>(
        R.layout.item_station,
        Locale("tr-TR")
    ) {

    private var listener: StationListener? = null

    interface StationListener {
        fun onClickFavorite(item: WorldFavorite)
        fun onClickTravel(item: WorldFavorite)
    }

    fun setListener(stationListener: StationListener) {
        this.listener = null
        this.listener = stationListener
    }

    override fun getRecyclerFilterItem(
        constLowerCase: String,
        value: WorldFavorite,
        controlParameter: String
    ): WorldFavorite? {
        val name = value.name.toLowerCase(Locale("tr-TR"))
        if(name.contains(constLowerCase)){
            return value
        }
        return null
    }

    @SuppressLint("SetTextI18n")
    override fun setBindingModel(item: WorldFavorite, binding: ItemStationBinding) {
        binding.tvCapacity.text = "Kapasite: ${item.capacity}"
        binding.tvCordinate.text = "Kordinat: ${item.coordinateX},${item.coordinateY}"
        binding.tvName.text = item.name
        binding.tvNeed.text = "İhtiyaç: ${item.need}"
        binding.tvStock.text = "Stok: ${item.stock}"

        if (item.isFavorite) {
            binding.imgFollow.setBackgroundResource(R.drawable.ic_star)
        } else {
            binding.imgFollow.setBackgroundResource(R.drawable.ic_unstar)
        }

        binding.imgFollow.setOnClickListener {
            listener?.onClickFavorite(item)
        }


        binding.btnTravel.setOnClickListener {
            listener?.onClickTravel(item)
        }
        if (type == 0) {
            binding.btnTravel.visibility = View.VISIBLE
        } else {
            binding.btnTravel.visibility = View.GONE
        }


        if(item.isItDone){
            binding.btnTravel.isEnabled = false
            binding.btnTravel.text = "Seyehat Edildi"
        }else{
            binding.btnTravel.isEnabled = true
            binding.btnTravel.text = "Seyehat et"
        }

    }
}