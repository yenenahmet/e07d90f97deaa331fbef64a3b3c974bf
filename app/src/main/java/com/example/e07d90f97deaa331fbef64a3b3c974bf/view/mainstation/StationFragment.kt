package com.example.e07d90f97deaa331fbef64a3b3c974bf.view.mainstation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LifecycleOwner
import com.example.e07d90f97deaa331fbef64a3b3c974bf.R
import com.example.e07d90f97deaa331fbef64a3b3c974bf.adater.StationAdapter
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.Spacecraft
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.WorldFavorite
import com.example.e07d90f97deaa331fbef64a3b3c974bf.databinding.FragmentStationBinding
import com.example.e07d90f97deaa331fbef64a3b3c974bf.viewmodel.mainstation.StationViewModel
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerFragment
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants

class StationFragment : BaseDaggerFragment<StationViewModel, FragmentStationBinding>(
    StationViewModel::class.java,
    R.layout.fragment_station
), StationAdapter.StationListener {

    private var spacecraft: Spacecraft? = null
    private var countDownTimer: CountDownTimer? = null
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            val text = s?.toString()?.trim()
            viewModel.adapter.setFilter("00${text}")
        }
    }

    override fun initViewModel(viewModel: StationViewModel) {
        binding.viewModel = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.adapter.setListener(this)
        binding.edtSearch.removeTextChangedListener(textWatcher)
        binding.edtSearch.addTextChangedListener(textWatcher)
    }

    @SuppressLint("SetTextI18n")
    override fun createLiveData(lifecycleOwner: LifecycleOwner) {
        viewModel.getWorldLiveData().observe(lifecycleOwner, {
            it?.let {
                if (ProjectConstants.ERROR_STATUS == it.status) {
                    showToast(it.description ?: "")
                }
            }
        })
        viewModel.getWorldLocal().observe(lifecycleOwner, {
            it?.let {
                if(it.isEmpty()){ // kurguyu bu sekilde bırakıyorum
                    viewModel.getWorld()
                }else{
                    viewModel.adapter.setItems(it)
                }
            }
        })
        viewModel.getSpacecrafts().observe(lifecycleOwner, {
            it?.let {
                spacecraft = it[0]
                val ugs = (spacecraft?.capacity ?: 0)
                binding.tvUGS.text = "UGS: $ugs"
                val eus = (spacecraft?.speed ?: 0)
                binding.tvEUS.text = "EUS: $eus"
                val ds = (spacecraft?.durability ?: 0)
                binding.tvDS.text = "DS: $ds"
                binding.tvDamage.text = (spacecraft?.damage ?: 0).toString()
                binding.tvName.text = it[0].name
                createCountDownTimer(ds.toLong())

            }
        })
    }

    override fun onClickFavorite(item: WorldFavorite) {
        item.isFavorite = !item.isFavorite
        viewModel.addWorldFavorite(item)
    }

    override fun onClickTravel(item: WorldFavorite) {
        val mX = item.coordinateX - (spacecraft?.lastCoordinateX ?: 0)
        val mY = item.coordinateY - (spacecraft?.lastCoordinateY ?: 0)
        var mTotal = if ((mX > 0 && mY > 0) || (mX < 0 && mY < 0)) {
            mX + mY
        } else {
            mX - mY
        }
        if (mTotal < 0) {
            mTotal *= -1
        }
        // hesalamalar düzgün olsun diye pisagor'a girmedim  *
        val ds = (spacecraft?.durability ?: 0)
        val eus = (spacecraft?.speed ?: 0)
        val damage = (spacecraft?.damage ?: 0)
        if ((spacecraft?.capacity ?: 0) >= item.need && eus >= mTotal && ds > 0 && damage > 0) {
            item.isItDone = true
            spacecraft?.let {
                it.lastCoordinateX = item.coordinateX
                it.lastCoordinateY = item.coordinateY
                it.capacity = (it.capacity - item.need)
                it.speed = it.speed - mTotal
                viewModel.updateSpacecraft(it)
                item.need = 0
                item.stock = item.capacity
                viewModel.addWorldFavorite(item)
            }
        } else {
            showToast("Seyehat gerçekletirilemiyor! Kapasiteniz, süreniz veya dayanıklılığınız bitti. ")
        }
    }

    private fun createCountDownTimer(ss: Long) {
        if (countDownTimer == null) {
            countDownTimer = object : CountDownTimer(ss, 1000) {
                @SuppressLint("SetTextI18n")
                override fun onTick(millisUntilFinished: Long) {
                    val second = millisUntilFinished / 1000
                    binding.tvTime.text = "${second}s"
                }

                override fun onFinish() {
                    spacecraft?.let {
                        it.damage = it.damage - 10
                        viewModel.updateSpacecraft(it)
                        if (it.damage > 0)
                            timerStart()
                    }
                }
            }
            timerStart()
        }
    }

    private fun timerStart() {
        countDownTimer?.start()
    }
}