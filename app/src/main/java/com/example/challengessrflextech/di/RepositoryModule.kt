package com.example.challengessrflextech.di

import com.example.challengessrflextech.di.repository.MainRepository
import com.example.challengessrflextech.di.repository.MainRepositoryContract
import com.example.challengessrflextech.ui.view.MainActivity
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val RepositoryModule = module {
    factory<MainRepositoryContract> { MainRepository(get()) }
}