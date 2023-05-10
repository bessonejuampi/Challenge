package com.example.challengessrflextech.ui.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.challengessrflextech.di.repository.MainRepository
import com.example.challengessrflextech.di.repository.MainRepositoryContract

class MainViewModel(
    private val context:Context,
    private val repository: MainRepositoryContract
):ViewModel() {
}