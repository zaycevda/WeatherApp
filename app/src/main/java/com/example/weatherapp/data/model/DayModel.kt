package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class DayModel(
    @SerializedName("avgtemp_c")
    val temp: Double,
    @SerializedName("maxwind_kph")
    val windSpeed: Double,
    @SerializedName("avghumidity")
    val humidity: Double,
    @SerializedName("condition")
    val conditionModel: ConditionModel
)