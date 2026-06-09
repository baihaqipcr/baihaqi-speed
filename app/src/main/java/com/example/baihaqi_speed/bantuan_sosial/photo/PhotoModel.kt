package com.example.baihaqi_speed.bantuan_sosial.photo

import com.google.gson.annotations.SerializedName

data class PhotoModel(
    val id: String,
    @SerializedName("author") // Mapping dari API (misal: tetap pakai Picsum sebagai dummy)
    val title: String,
    val location: String = "Kabupaten Sleman, DIY", // Default value untuk tema desa
    val description: String = "Program pemberdayaan masyarakat untuk meningkatkan kesejahteraan desa.",
    @SerializedName("download_url")
    val imageUrl: String
)