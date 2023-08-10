package com.example.weatherapp.app.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.app.ui.adapter.DaysAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind, R.id.ll_home)

    private var adapter: DaysAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRV()
    }

    override fun onDestroy() {
        adapter = null
        super.onDestroy()
    }

    private fun initRV() {
        adapter = DaysAdapter()
        binding.rvDays.adapter = adapter
    }
}