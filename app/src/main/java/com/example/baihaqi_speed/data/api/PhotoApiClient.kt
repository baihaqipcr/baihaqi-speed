package com.example.baihaqi_speed.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PhotoApiClient {

    private const val BASE_URL = "https://api.unsplash.com/"

    // Ganti dengan Access Key dari unsplash.com/developers
    const val ACCESS_KEY = "wMQ44jcPXQJKN_bLhvA_Jgt0fncnX0bgQ0reX-jjjX0"

    val apiService: PhotoApiService by lazy {

        // Interceptor untuk menambahkan Authorization header otomatis
        val authInterceptor = okhttp3.Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Client-ID $ACCESS_KEY")
                .addHeader("Accept-Version", "v1")
                .build()
            chain.proceed(request)
        }

        // Logging (tampil di Logcat saat debug)
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PhotoApiService::class.java)
    }
}