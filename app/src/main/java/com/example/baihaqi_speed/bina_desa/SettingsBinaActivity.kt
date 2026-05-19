package com.example.baihaqi_speed.bina_desa

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.databinding.ActivitySettingsBinaBinding

class SettingsBinaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupListView()
        setupFeedback()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupListView() {
        val settingsItems = arrayOf(
            "Tentang Aplikasi",
            "Kebijakan Privasi",
            "Syarat & Ketentuan",
            "Bantuan & Dukungan",
            "Hubungi Kami",
            "Lisensi Open Source"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            settingsItems
        )

        binding.lvSettings.adapter = adapter

        binding.lvSettings.setOnItemClickListener { _, _, position, _ ->
            val item = settingsItems[position]
            when (item) {
                "Tentang Aplikasi" -> {
                    // Bisa navigasi ke fragment About yang sudah ada atau activity baru
                    Toast.makeText(this, "Membuka $item...", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Fitur $item segera hadir", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupFeedback() {
        binding.btnSendFeedback.setOnClickListener {
            val feedback = binding.etFeedback.text.toString().trim()
            if (feedback.isNotEmpty()) {
                Toast.makeText(this, "Terima kasih atas masukan Anda!", Toast.LENGTH_LONG).show()
                binding.etFeedback.text?.clear()
            } else {
                binding.etFeedback.error = "Mohon isi masukan Anda"
            }
        }
    }
}
