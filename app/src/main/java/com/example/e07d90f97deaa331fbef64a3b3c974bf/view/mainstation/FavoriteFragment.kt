package com.example.e07d90f97deaa331fbef64a3b3c974bf.view.mainstation

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.e07d90f97deaa331fbef64a3b3c974bf.R
import com.example.e07d90f97deaa331fbef64a3b3c974bf.adater.StationAdapter
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.WorldFavorite
import com.example.e07d90f97deaa331fbef64a3b3c974bf.databinding.FragmentFavoriteBinding
import com.example.e07d90f97deaa331fbef64a3b3c974bf.viewmodel.mainstation.FavoriteViewModel
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerFragment

class FavoriteFragment : BaseDaggerFragment<FavoriteViewModel,FragmentFavoriteBinding>(
    FavoriteViewModel::class.java,
    R.layout.fragment_favorite
) ,StationAdapter.StationListener{

    override fun initViewModel(viewModel: FavoriteViewModel) {
        binding.viewModel = viewModel
    }


    override fun createLiveData(lifecycleOwner: LifecycleOwner) {
        viewModel.getWorldFavorites().observe(lifecycleOwner, Observer {
            it?.let {
                viewModel.adapter.setItems(it)
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.adapter.setListener(this)
    }

    override fun onClickFavorite(item: WorldFavorite) {
        item.isFavorite = !item.isFavorite
        viewModel.addWorldFavorite(item)
    }

    override fun onClickTravel(item: WorldFavorite) {

    }

}