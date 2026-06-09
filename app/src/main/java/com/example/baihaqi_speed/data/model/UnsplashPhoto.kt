package com.example.baihaqi_speed.data.model

import com.google.gson.annotations.SerializedName

data class UnsplashSearchResponse(
    @SerializedName("results")
    val results: List<UnsplashPhoto>
)

data class UnsplashPhoto(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("alt_description")
    val altDescription: String?,
    @SerializedName("urls")
    val urls: UnsplashUrls,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("user")
    val user: UnsplashUser
)

data class UnsplashUrls(
    @SerializedName("raw")
    val raw: String,
    @SerializedName("full")
    val full: String,
    @SerializedName("regular")
    val regular: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("thumb")
    val thumb: String
)

data class UnsplashUser(
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("location")
    val location: String?,
    @SerializedName("bio")
    val bio: String?
)
