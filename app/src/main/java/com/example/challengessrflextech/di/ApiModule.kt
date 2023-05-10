package com.example.challengessrflextech.di

import android.content.Context
import com.example.challengessrflextech.data.network.ApiClient
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



    val ApiModule = module {
        fun provideStockApi(retrofit: Retrofit): ApiClient {
            return retrofit.create(ApiClient::class.java)
        }
        single { provideStockApi(get()) }
    }

    val NetworkModule = module {

        fun provideHttpClient(context: Context): OkHttpClient {
            val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(8000, TimeUnit.SECONDS)
                .readTimeout(8000, TimeUnit.SECONDS)

            okHttpClientBuilder.build()
            return okHttpClientBuilder.build()
        }

        fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }

        single { provideHttpClient(androidContext()) }
        single {
            val baseUrl = "https://thecatapi.com/"
            provideRetrofit(get(), baseUrl)
        }
    }