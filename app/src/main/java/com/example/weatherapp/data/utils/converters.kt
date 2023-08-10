package com.example.weatherapp.data.utils

import com.example.weatherapp.data.model.ConditionModel
import com.example.weatherapp.data.model.DayModel
import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.domain.model.Condition
import com.example.weatherapp.domain.model.Day
import com.example.weatherapp.domain.model.Weather

fun WeatherModel.toWeather() =
    Weather(
        date = this.date,
        day = this.dayModel.toDay()
    )

private fun DayModel.toDay() =
    Day(
        temp = temp,
        windSpeed = windSpeed,
        humidity = humidity,
        condition = conditionModel.toCondition()
    )

private fun ConditionModel.toCondition() =
    Condition(
        description = description,
        icon = icon
    )