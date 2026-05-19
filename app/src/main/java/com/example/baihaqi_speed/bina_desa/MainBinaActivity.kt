package com.example.baihaqi_speed.bina_desa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.databinding.ActivityMainBinaBinding

class MainBinaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinaBinding
    
    companion object {
        private const val TAG = "MainBinaActivity"
        const val EXTRA_PROGRAM_ID    = "extra_program_id"
        const val EXTRA_PROGRAM_TITLE = "extra_program_title"
        const val BINA_DESA_URL       = "https://bantuansosial-admin.alwaysdata.net/login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil username dari SharedPreferences
        val pref     = getSharedPreferences(SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE)
        val username = pref.getString(SplashBinaActivity.KEY_USER, "Relawan") ?: "Relawan"

        // Setup toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tvToolbarTitle.text = "Bina Desa"

        Log.d(TAG, "MainBinaActivity — user: $username")

        setupClickListeners()
        setupChips()
    }

    private fun setupClickListeners() {

        // Tombol WebView di toolbar
        binding.btnWebView.setOnClickListener {
            Log.d(TAG, "Buka WebView")
            startActivity(Intent(this, WebBinaActivity::class.java).apply {
                putExtra(WebBinaActivity.EXTRA_URL, BINA_DESA_URL)
                putExtra(WebBinaActivity.EXTRA_TITLE, "Bina Desa")
            })
        }

        // Navigasi ke Settings (ListView Menu) via "Lihat Semua" di Program Unggulan
        // Atau bisa juga ditambahkan di toolbar jika diinginkan
        binding.tvSeeAllPopular.setOnClickListener {
            startActivity(Intent(this, SettingsBinaActivity::class.java))
        }

        // Card Program Unggulan
        binding.cardPopular1.setOnClickListener {
            goToDetail("1", "Bansos Sembako")
        }

        binding.cardPopular2.setOnClickListener {
            goToDetail("2", "Bedah Rumah")
        }
        
        // Search functionality placeholder
        binding.tilSearch.setEndIconOnClickListener {
            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()) {
                Toast.makeText(this, "Mencari: $query", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun setupChips() {
        binding.chipGroupCategories.setOnCheckedStateChangeListener { group, checkedIds ->
            val chipName = when (checkedIds.firstOrNull()) {
                binding.chipSemua.id -> "Semua"
                binding.chipSembako.id -> "Sembako"
                binding.chipKesehatan.id -> "Kesehatan"
                binding.chipPendidikan.id -> "Pendidikan"
                else -> "None"
            }
            if (chipName != "None") {
                Toast.makeText(this, "Kategori: $chipName", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToDetail(id: String, title: String) {
        Log.d(TAG, "Buka detail: $title")
        startActivity(Intent(this, DetailBinaActivity::class.java).apply {
            putExtra(EXTRA_PROGRAM_ID, id)
            putExtra(EXTRA_PROGRAM_TITLE, title)
        })
    }
}
