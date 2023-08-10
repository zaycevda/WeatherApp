package com.example.weatherapp.data.service

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.model.ResponseModel
import retrofit2.http.GET

interface WeatherApi {
    @GET("forecast.json?q=moscow&days=5&key=$API_KEY")
    suspend fun getWeather(): ResponseModel

    private companion object {
        private const val API_KEY = BuildConfig.API_KEY
    }
}