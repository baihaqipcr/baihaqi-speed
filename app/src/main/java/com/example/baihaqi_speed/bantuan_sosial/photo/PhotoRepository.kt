package com.example.baihaqi_speed.bantuan_sosial.photo

import com.example.baihaqi_speed.data.api.PhotoApiClient
import com.example.baihaqi_speed.data.model.UnsplashPhoto
import com.example.baihaqi_speed.data.model.UnsplashUrls
import com.example.baihaqi_speed.data.model.UnsplashUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoRepository {

    private val apiService = PhotoApiClient.apiService

    // Kategori query sesuai tema Bina Desa
    private val queryMap = mapOf(
        "semua"        to "social aid community village Indonesia",
        "sembako"      to "food distribution rice Indonesia village",
        "kesehatan"    to "community health Indonesia posyandu",
        "pendidikan"   to "education community Indonesia school village",
        "infrastruktur" to "village development Indonesia construction"
    )

    /**
     * Ambil foto berdasarkan kategori chip yang dipilih
     */
    suspend fun getPhotosByCategory(category: String = "semua"): Result<List<UnsplashPhoto>> {
        return withContext(Dispatchers.IO) {
            try {
                val query = queryMap[category.lowercase()] ?: queryMap["semua"]!!
                val response = apiService.getKegiatanBinaDesa(
                    query = query,
                    perPage = 10,
                    page = 1
                )
                Result.success(response.results)
            } catch (e: Exception) {
                // Fallback ke local data jika API gagal
                Result.success(getLocalFallbackPhotos())
            }
        }
    }

    /**
     * Data lokal sebagai fallback jika internet tidak tersedia
     */
    private fun getLocalFallbackPhotos(): List<UnsplashPhoto> {
        val seeds = listOf(
            Triple("distribusi-sembako",   "Distribusi Sembako Bulanan",      "Sukamakmur"),
            Triple("posyandu-desa",         "Kegiatan Posyandu Desa",          "Desa Maju"),
            Triple("bedah-rumah",           "Program Bedah Rumah",             "Desa Harapan"),
            Triple("pelatihan-wirausaha",   "Pelatihan Wirausaha Ibu PKK",     "Balai Desa"),
            Triple("pendataan-warga",       "Pendataan Warga Penerima Bansos", "RT 03"),
            Triple("serah-terima-bantuan",  "Serah Terima Bantuan Sosial",     "Kantor Camat")
        )

        return seeds.map { (seed, title, location) ->
            UnsplashPhoto(
                id              = seed,
                description     = title,
                altDescription  = title,
                likes           = 0,
                urls            = UnsplashUrls(
                    raw = "https://picsum.photos/seed/$seed/800/600",
                    full = "https://picsum.photos/seed/$seed/800/600",
                    regular = "https://picsum.photos/seed/$seed/400/300",
                    small = "https://picsum.photos/seed/$seed/400/300",
                    thumb = "https://picsum.photos/seed/$seed/200/150"
                ),
                user = UnsplashUser(
                    name = "Tim Bina Desa",
                    username = "binadesa",
                    location = location,
                    bio = null
                )
            )
        }
    }
}
