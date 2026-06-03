package com.example.baihaqi_speed.data.api

import com.example.baihaqi_speed.bantuan_sosial.photo.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}
