package com.example.weatherapp.domain.model

data class Day(
    val temp: Double,
    val windSpeed: Double,
    val humidity: Double,
    val condition: Condition
)
