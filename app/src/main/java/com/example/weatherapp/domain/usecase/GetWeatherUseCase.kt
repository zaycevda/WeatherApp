package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.repository.WeatherRepository

class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute() = weatherRepository.getWeather()
}