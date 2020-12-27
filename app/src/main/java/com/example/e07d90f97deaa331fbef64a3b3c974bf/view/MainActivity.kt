package com.example.e07d90f97deaa331fbef64a3b3c974bf.view

import android.os.Bundle
import android.widget.SeekBar
import androidx.lifecycle.Observer
import com.example.e07d90f97deaa331fbef64a3b3c974bf.R
import com.example.e07d90f97deaa331fbef64a3b3c974bf.data.local.entities.Spacecraft
import com.example.e07d90f97deaa331fbef64a3b3c974bf.databinding.ActivityMainBinding
import com.example.e07d90f97deaa331fbef64a3b3c974bf.view.mainstation.MainStationActivity
import com.example.e07d90f97deaa331fbef64a3b3c974bf.viewmodel.main.MainViewModel
import com.yenen.ahmet.basecorelibrary.base.extension.showToast
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerActivity
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants

class MainActivity : BaseDaggerActivity<MainViewModel, ActivityMainBinding>(
    MainViewModel::class.java,
    R.layout.activity_main
) {

    private var totalSeek = 15
    private var currentSeek =0
    override fun initViewModel(viewModel: MainViewModel) {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getLiveDataAddSpacecraft().observe(this, Observer {
            it?.let {
                if (it.status == ProjectConstants.SUCCESS_STATUS) {
                    startActivity(MainStationActivity::class.java)
                    finish()
                } else {
                    showToast(it.description ?: "")
                }
            }
        })
    }


    override fun onBindingCreate(binding: ActivityMainBinding) {
        binding.btnNext.setOnClickListener {
            next()
        }

        binding.seekBarCapacity.setOnSeekBarChangeListener(seekBarListener)
        binding.seekBarDurability.setOnSeekBarChangeListener(seekBarListener)
        binding.seekBarSpeed.setOnSeekBarChangeListener(seekBarListener)
    }

    private val seekBarListener = object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            val capacity = binding.seekBarCapacity.progress
            val speed = binding.seekBarSpeed.progress
            val durability = binding.seekBarDurability.progress

            currentSeek =  capacity+speed+durability
            binding.tvTotalSeek.text = (totalSeek - currentSeek).toString()

        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {

        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {

        }
    }

    private fun next() {
        val name = binding.edtName.text?.trim().toString()
        val durability = binding.seekBarDurability.progress
        val speed = binding.seekBarSpeed.progress
        val capacity = binding.seekBarCapacity.progress
        if (name.length < 3) {
            showToast("Lütfen bir isim giriniz")
            return
        }
        if (1 > durability) {
            showToast("Lütfen en az bir birim olacak şekilde dayanıklık verin!")
            return
        }
        if (1 > speed) {
            showToast("Lütfen en az bir birim olacak şekilde hız verin!")
            return
        }
        if (1 > speed) {
            showToast("Lütfen en az bir birim olacak şekilde hız verin!")
            return
        }

        if(currentSeek !=15){
            showToast("Lütfen 15 birim olacak şekilde puan veriniz!")
            return
        }
        viewModel.clear()
        val data = Spacecraft(
            name,
            durability * 10000,
            speed * 20,
            capacity * 10000, 0, 0, 100,
            "ID"
        )
        viewModel.addSpacecraft(data)
    }


}