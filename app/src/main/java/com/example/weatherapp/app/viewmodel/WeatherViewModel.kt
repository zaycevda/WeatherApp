package com.example.weatherapp.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.app.viewmodel.ScreenState.ErrorScreenState
import com.example.weatherapp.app.viewmodel.ScreenState.LoadingScreenState
import com.example.weatherapp.app.viewmodel.ScreenState.SuccessScreenState
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weather = MutableStateFlow<ScreenState<List<Weather>>>(LoadingScreenState())
    val weather = _weather.asStateFlow()

    fun getWeather() {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            _weather.value = ErrorScreenState(throwable)
        }

        viewModelScope.launch(exceptionHandler) {
            _weather.value = LoadingScreenState()
            val weathers = getWeatherUseCase.execute()
            _weather.value = SuccessScreenState(weathers)
        }
    }
}