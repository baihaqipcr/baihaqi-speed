package com.example.baihaqi_speed.bina_desa

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.databinding.ActivityDetailBinaBinding

class DetailBinaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinaBinding
    companion object { private const val TAG = "DetailBinaActivity" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val programId    = intent.getStringExtra(MainBinaActivity.EXTRA_PROGRAM_ID) ?: "1"
        val programTitle = intent.getStringExtra(MainBinaActivity.EXTRA_PROGRAM_TITLE) ?: "Program"

        // Toolbar dengan tombol back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        Log.d(TAG, "Detail program ID=$programId title=$programTitle")

        // Set data ke view (bisa dikembangkan dengan data class/repository)
        binding.tvDetailTitle.text = programTitle

        binding.btnDaftar.setOnClickListener {
            Toast.makeText(this, "Pendaftaran $programTitle berhasil!", Toast.LENGTH_SHORT).show()
            Log.i(TAG, "Daftar program: $programTitle")
        }

        // Tombol WebView di detail
        binding.btnWebView.setOnClickListener {
            startActivity(Intent(this, WebBinaActivity::class.java).apply {
                putExtra(WebBinaActivity.EXTRA_URL, "https://binades.id/program/$programId")
                putExtra(WebBinaActivity.EXTRA_TITLE, programTitle)
            })
        }
    }

    // Tombol back toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}