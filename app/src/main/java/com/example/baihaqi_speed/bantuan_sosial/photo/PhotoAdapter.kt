package com.example.baihaqi_speed.bantuan_sosial.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baihaqi_speed.databinding.ItemPhotoBinding

class PhotoAdapter(private val items: List<PhotoModel>) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            tvTitle.text = item.title
            tvLocation.text = item.location
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .centerCrop()
                .into(imgPhoto)
        }
    }

    override fun getItemCount(): Int = items.size
}