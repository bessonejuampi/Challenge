package com.example.challengessrflextech.di.repository

import android.content.Context
import com.example.challengessrflextech.data.models.Cat
import com.example.challengessrflextech.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface MainRepositoryContract{
    suspend fun getImagesRelated(keyWord:String):List<Cat>?
}

class MainRepository(private val apiClient: ApiClient) : MainRepositoryContract {
    override suspend fun getImagesRelated(keyWord: String): List<Cat>? {
        var listCat: List<Cat>? = null
        val response = apiClient.getImagesRelated(keyWord = keyWord)
        if (response.isSuccessful) {
            listCat = response.body()
        }
        return listCat
    }
}