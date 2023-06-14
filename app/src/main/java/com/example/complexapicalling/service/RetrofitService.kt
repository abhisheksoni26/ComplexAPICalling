package com.example.complexapicalling.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




//const val BASE_URL=" https://newsapi.org/v2/"
const val BASE_URL = "https://pokeapi.co/api/v2/"

object RetrofitService {
    //val interceptor = HttpLoggingInterceptor()
    //interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}