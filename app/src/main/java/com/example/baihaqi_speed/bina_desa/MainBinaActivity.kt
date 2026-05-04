package com.example.baihaqi_speed.bina_desa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        val username = pref.getString(SplashBinaActivity.KEY_USER, "User") ?: "User"

        // Setup toolbar (tanpa back button di halaman utama)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.tvToolbarTitle.text = "Bina Desa"

        Log.d(TAG, "MainBinaActivity — user: $username")

        setupClickListeners()
    }

    private fun setupClickListeners() {

        // Tombol WebView di toolbar
        binding.btnWebView.setOnClickListener {
            Log.d(TAG, "Buka WebView: $BINA_DESA_URL")
            startActivity(Intent(this, WebBinaActivity::class.java).apply {
                putExtra(WebBinaActivity.EXTRA_URL, BINA_DESA_URL)
                putExtra(WebBinaActivity.EXTRA_TITLE, "Bina Desa")
            })
        }

        // Card Popular 1 → Detail
        binding.cardPopular1.setOnClickListener {
            goToDetail("1", "Bansos Sembako")
        }

        // Card Popular 2 → Detail
        binding.cardPopular2.setOnClickListener {
            goToDetail("2", "Bedah Rumah")
        }

        // Card Recommended 1 → Detail
        binding.cardRec1.setOnClickListener {
            goToDetail("3", "Sembako Gratis")
        }

        // Card Recommended 2 → Detail
        binding.cardRec2.setOnClickListener {
            goToDetail("4", "Bangun Desa")
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
