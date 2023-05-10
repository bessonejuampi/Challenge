package com.example.challengessrflextech

import android.app.Application
import com.example.challengessrflextech.di.ApiModule
import com.example.challengessrflextech.di.NetworkModule
import com.example.challengessrflextech.di.RepositoryModule
import com.example.challengessrflextech.di.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppController)
            modules(arrayListOf(NetworkModule, ApiModule ,RepositoryModule, ViewModelsModule))
        }
    }
}