package com.example.challengessrflextech.di

import com.example.challengessrflextech.ui.view.MainActivity
import com.example.challengessrflextech.di.repository.MainRepositoryContract
import com.example.challengessrflextech.ui.viewModels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelsModule = module {
    viewModel { MainViewModel( get()) }
}