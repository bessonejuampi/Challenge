package com.example.challengessrflextech.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.challengessrflextech.R
import com.example.challengessrflextech.ui.viewModels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}