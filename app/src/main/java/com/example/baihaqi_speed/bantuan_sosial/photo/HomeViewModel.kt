package com.example.baihaqi_speed.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baihaqi_speed.bantuan_sosial.photo.PhotoRepository
import com.example.baihaqi_speed.data.model.UnsplashPhoto
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = PhotoRepository()

    private val _photos = MutableLiveData<List<UnsplashPhoto>>()
    val photos: LiveData<List<UnsplashPhoto>> = _photos

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadPhotos("semua")
    }

    // Dipanggil ketika user klik Chip kategori
    fun loadPhotos(category: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getPhotosByCategory(category)
            result.onSuccess { photos ->
                _photos.value = photos
            }
            _isLoading.value = false
        }
    }
}