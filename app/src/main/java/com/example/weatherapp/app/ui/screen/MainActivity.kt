package com.example.weatherapp.app.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.app.App
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.app.ui.adapter.DaysAdapter
import com.example.weatherapp.app.utils.showToast
import com.example.weatherapp.app.viewmodel.WeatherViewModel
import com.example.weatherapp.app.viewmodel.WeatherViewModelFactory
import com.example.weatherapp.domain.model.Weather
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var weatherViewModelFactory: WeatherViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, weatherViewModelFactory)[WeatherViewModel::class.java]
    }

    private val binding by viewBinding(ActivityMainBinding::bind, R.id.fl_home)

    private var adapter: DaysAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        initRV()
        getWeathers()
    }

    override fun onDestroy() {
        adapter = null
        super.onDestroy()
    }

    private fun inject() {
        App.appComponent.injectMainActivity(activity = this)
    }

    private fun initRV() {
        adapter = DaysAdapter()
        binding.rvDays.adapter = adapter
    }

    private fun getWeathers() {
        viewModel.getWeather()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.weather.collect { state ->
                    state.on(
                        loading = {
                            binding.pgHome.isGone = false
                            binding.llScreen.isGone = true
                        },
                        success = { weathers ->

                            binding.pgHome.isGone = true
                            binding.llScreen.isGone = false

                            val weathersForAdapter = mutableListOf<Weather>()

                            /*
                            we got 5 days from api, but first of them goes to mcv_current_weather
                            and only 4 to adapter, so we separate the list
                            */
                            for (i in 1 until weathers.count()) {
                                val weather = weathers[i]
                                weathersForAdapter.add(weather)
                            }

                            adapter?.weathers = weathersForAdapter

                            weathers[0].let { weather ->
                                val day = weather.day
                                val condition = day.condition
                                val humidity = day.humidity.roundToInt().toString()
                                val temp = day.temp.toString()
                                val windSpeed = day.windSpeed.toString()
                                val description = condition.description
                                val icon = condition.icon
                                val iconLink = "${PROTOCOL}$icon"

                                binding.wivHumidity.setData(getString(R.string.weather_humidity, humidity))
                                binding.tvTemp.text = getString(R.string.weather_temp, temp)
                                binding.wivWindSpeed.setData(getString(R.string.weather_wind_speed, windSpeed))
                                binding.wivDescription.setData(getString(R.string.weather_description, description))
                                Glide.with(this@MainActivity).load(iconLink).into(binding.ivCondition)
                            }
                        },
                        error = { throwable ->

                            binding.pgHome.isGone = true
                            binding.llScreen.isGone = false

                            showToast(throwable.message.toString())
                        }
                    )
                }
            }
        }
    }

    private companion object {
        private const val PROTOCOL = "https:"
    }
}