package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class ConditionModel(
    @SerializedName("text")
    val description: String,
    @SerializedName("icon")
    val icon: String
)