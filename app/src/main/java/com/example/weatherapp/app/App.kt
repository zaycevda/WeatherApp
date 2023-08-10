package com.example.weatherapp.app

import android.app.Application
import com.example.weatherapp.app.di.component.AppComponent
import com.example.weatherapp.app.di.component.DaggerAppComponent
import com.example.weatherapp.app.di.module.AppModule
import com.example.weatherapp.app.di.module.DataModule
import com.example.weatherapp.app.di.module.DomainModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context = this))
            .dataModule(DataModule())
            .domainModule(DomainModule())
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }
}