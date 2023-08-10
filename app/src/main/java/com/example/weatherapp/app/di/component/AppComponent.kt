package com.example.weatherapp.app.di.component

import com.example.weatherapp.app.di.module.AppModule
import com.example.weatherapp.app.di.module.DataModule
import com.example.weatherapp.app.di.module.DomainModule
import com.example.weatherapp.app.ui.screen.MainActivity
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun injectMainActivity(activity: MainActivity)
}