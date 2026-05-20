package com.example.baihaqi_speed.bantuan_sosial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baihaqi_speed.R
import com.example.baihaqi_speed.databinding.ItemProgramBinding

class ProgramAdapter(
    private var programList: List<ProgramsModel>,
    private val onItemClick: (ProgramsModel) -> Unit
) : RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>() {

    inner class ProgramViewHolder(val binding: ItemProgramBinding) : 
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val binding = ItemProgramBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProgramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        val item = programList[position]
        with(holder.binding) {
            tvProgramName.text = item.name
            tvProgramValue.text = item.value
            tvProgramDesc.text = item.description

            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.bd_bg_splash)
                .error(android.R.drawable.ic_menu_gallery)
                .into(imgProgram)

            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun getItemCount(): Int = programList.size

    fun updateData(newList: List<ProgramsModel>) {
        this.programList = newList
        notifyDataSetChanged()
    }
}
