package com.example.baihaqi_speed.bantuan_sosial.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide // 🔷 Solusi Red 1: Import Glide yang tadinya hilang
import com.example.baihaqi_speed.databinding.ItemPhotoBinding

// 🔷 Catatan: Jika PhotoModel berada di folder lain, Android Studio akan otomatis
// meminta import di sini. Cukup tekan Alt + Enter pada PhotoModel.

class PhotoAdapter(private val items: List<PhotoModel>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvAuthor.text = item.author

        // Glide sekarang aman dan tidak merah lagi
        Glide.with(holder.itemView.context)
            .load(item.download_url)
            .into(holder.binding.imgPhoto)
    }

    override fun getItemCount(): Int = items.size
}