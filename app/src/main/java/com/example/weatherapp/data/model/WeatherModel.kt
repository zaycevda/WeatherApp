package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("date")
    val date: String,
    @SerializedName("day")
    val dayModel: DayModel
)