package com.example.weatherapp.data.repository

import com.example.weatherapp.data.service.WeatherApi
import com.example.weatherapp.data.utils.toWeather
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {
    override suspend fun getWeather(): List<Weather> {
        val forecast = weatherApi.getWeather().forecast
        val weather = forecast.weatherModels
        return weather.map { weatherModel -> weatherModel.toWeather() }
    }
}