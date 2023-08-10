package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ForecastModel(
    @SerializedName("forecastday")
    val weatherModels: List<WeatherModel>
)