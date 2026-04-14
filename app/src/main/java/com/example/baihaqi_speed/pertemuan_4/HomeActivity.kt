package com.example.baihaqi_speed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.databinding.ActivityHomeBinding
import com.example.baihaqi_speed.pertemuan_3.LoginActivity
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    companion object {
        private const val TAG = "HomeActivity"

        // Judul dan deskripsi halaman utama — akan dikirim ke semua sub-halaman
        const val HOME_TITLE = "AquaFlow"
        const val HOME_DESC  = "Kelola & hitung bangun geometri dengan mudah"

        // Key untuk Intent extras
        const val EXTRA_HOME_TITLE    = "extra_home_title"
        const val EXTRA_HOME_DESC     = "extra_home_desc"
        const val EXTRA_USERNAME      = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: HomeActivity started")

        // Ambil username yang dikirim dari LoginActivity
        val username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME) ?: "User"
        binding.tvHomeGreeting.text = "Hello, $username"

        setupButtonListeners()
    }

    private fun setupButtonListeners() {

        // Tombol 1 — Bangun Ruang
        binding.cardBangunRuang.setOnClickListener {
            Log.d(TAG, "Navigasi ke BangunRuangActivity")
            val intent = Intent(this, BangunRuangActivity::class.java).apply {
                putExtra(EXTRA_HOME_TITLE, HOME_TITLE)
                putExtra(EXTRA_HOME_DESC, HOME_DESC)
            }
            startActivity(intent)
        }

        // Tombol 2 — Custom 1 (Galeri Bentuk)
        binding.cardCustomOne.setOnClickListener {
            Log.d(TAG, "Navigasi ke CustomOneActivity")
            val intent = Intent(this, CustomOneActivity::class.java).apply {
                putExtra(EXTRA_HOME_TITLE, HOME_TITLE)
                putExtra(EXTRA_HOME_DESC, HOME_DESC)
            }
            startActivity(intent)
        }

        // Tombol 3 — Custom 2 (Tips Penggunaan)
        binding.cardCustomTwo.setOnClickListener {
            Log.d(TAG, "Navigasi ke CustomTwoActivity")
            val intent = Intent(this, CustomTwoActivity::class.java).apply {
                putExtra(EXTRA_HOME_TITLE, HOME_TITLE)
                putExtra(EXTRA_HOME_DESC, HOME_DESC)
            }
            startActivity(intent)
        }

        // Tombol 4 — Logout dengan konfirmasi
        binding.cardLogout.setOnClickListener {
            Log.d(TAG, "Tombol Logout diklik")
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah kamu yakin ingin keluar dari akun?")
            .setIcon(R.drawable.ic_exit)
            .setPositiveButton("Ya, Logout") { _, _ ->
                Log.i(TAG, "User memilih logout")
                // Kembali ke LoginActivity dan bersihkan semua back stack
                val intent = Intent(this, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
                Log.d(TAG, "Logout dibatalkan")
                // Tampilkan Snackbar
                Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
            }
            .setCancelable(true)
            .show()
    }
}