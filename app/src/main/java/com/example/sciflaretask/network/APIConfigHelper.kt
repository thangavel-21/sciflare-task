package com.terareum.exchange.model.network

import com.terareum.exchange.utills.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIConfigHelper {

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getHttpClient(): OkHttpClient {
        val httpLogging = HttpLoggingInterceptor()
        val httpClient = OkHttpClient.Builder()
        httpLogging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(httpLogging)
        return httpClient.build()
    }

    fun getRetrofitInstance(): APIServices {
        return getInstance().create(APIServices::class.java)
    }
}