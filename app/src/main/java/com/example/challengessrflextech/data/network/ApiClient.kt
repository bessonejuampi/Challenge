package com.example.challengessrflextech.data.network

import com.example.challengessrflextech.data.models.Cat
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiClient {
    @GET("images/search")
    suspend fun getImagesRelated(
        @Query("format") format : String = "json",
        @Query("limit") limit : Int = 10,
        @Query("sub_id") keyWord : String,
        @Header("Content-Type") contentType:String = "application/json",
        @Header("x-api-key") apiKey:String = "DEMO-API-KEY"

    ): Response<List<Cat>>
}