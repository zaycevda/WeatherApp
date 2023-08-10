package com.example.weatherapp.app.di.module

import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.data.service.WeatherApi
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {
    @Provides
    fun provideWeatherApi(): WeatherApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)

    @Provides
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository =
        WeatherRepositoryImpl(weatherApi = weatherApi)

    private companion object {
        private const val BASE_URL = "http://api.weatherapi.com/v1/"
    }
}