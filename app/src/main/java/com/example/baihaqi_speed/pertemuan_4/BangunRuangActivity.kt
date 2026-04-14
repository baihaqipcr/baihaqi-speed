package com.example.baihaqi_speed

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.databinding.ActivityBangunRuangBinding
import kotlin.math.PI

class BangunRuangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBangunRuangBinding
    companion object { private const val TAG = "BangunRuangActivity" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBangunRuangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tampilkan judul & deskripsi yang dikirim dari HomeActivity
        val homeTitle = intent.getStringExtra(HomeActivity.EXTRA_HOME_TITLE) ?: ""
        val homeDesc  = intent.getStringExtra(HomeActivity.EXTRA_HOME_DESC) ?: ""
        binding.tvFromHomeTitle.text = homeTitle
        binding.tvFromHomeDesc.text  = homeDesc
        binding.tvPageTitle.text     = "Bangun Ruang"

        Log.d(TAG, "Diterima dari HomeActivity → title: $homeTitle | desc: $homeDesc")

        binding.btnBack.setOnClickListener { finish() }

        binding.btnHitungBalok.setOnClickListener { hitungVolumeBalok() }
        binding.btnHitungTabung.setOnClickListener { hitungVolumeTabung() }
    }

    private fun hitungVolumeBalok() {
        val p = binding.etPanjang.text.toString().trim()
        val l = binding.etLebar.text.toString().trim()
        val t = binding.etTinggi.text.toString().trim()

        if (p.isEmpty() || l.isEmpty() || t.isEmpty()) {
            Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            return
        }
        val volume = p.toDouble() * l.toDouble() * t.toDouble()
        Log.i(TAG, "Volume Balok = $volume cm³")
        binding.tvHasilBalok.text = "Volume Balok = %.2f cm³".format(volume)
    }

    private fun hitungVolumeTabung() {
        val r = binding.etJariJari.text.toString().trim()
        val t = binding.etTinggiTabung.text.toString().trim()

        if (r.isEmpty() || t.isEmpty()) {
            Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            return
        }
        val volume = PI * r.toDouble() * r.toDouble() * t.toDouble()
        Log.i(TAG, "Volume Tabung = $volume cm³")
        binding.tvHasilTabung.text = "Volume Tabung = %.2f cm³".format(volume)
    }
}