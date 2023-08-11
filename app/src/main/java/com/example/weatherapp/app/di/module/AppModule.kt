package com.example.weatherapp.app.di.module

import android.content.Context
import com.example.weatherapp.app.viewmodel.WeatherViewModelFactory
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideContext() = context

    @Provides
    fun provideWeatherViewModelFactory(getWeatherUseCase: GetWeatherUseCase) =
        WeatherViewModelFactory(getWeatherUseCase = getWeatherUseCase)
}