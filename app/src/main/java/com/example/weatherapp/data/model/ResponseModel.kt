package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("forecast")
    val forecast: ForecastModel
)