package com.example.weatherapp.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.domain.usecase.GetWeatherUseCase

class WeatherViewModelFactory(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        WeatherViewModel(
            getWeatherUseCase = getWeatherUseCase
        ) as? T ?: throw IllegalArgumentException("WeatherViewModel not found")
}