package com.example.weatherapp.app.di.module

import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.usecase.GetWeatherUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetWeatherUseCase(weatherRepository: WeatherRepository) =
        GetWeatherUseCase(weatherRepository = weatherRepository)
}