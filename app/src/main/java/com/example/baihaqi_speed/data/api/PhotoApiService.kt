package com.example.baihaqi_speed.data.api

import com.example.baihaqi_speed.data.model.UnsplashSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {
    @GET("search/photos")
    suspend fun getKegiatanBinaDesa(
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("client_id") clientId: String = "YOUR_UNSPLASH_ACCESS_KEY" // Biasanya butuh API Key
    ): UnsplashSearchResponse
}
