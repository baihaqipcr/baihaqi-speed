package com.example.baihaqi_speed.bina_desa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.MainActivity
import com.example.baihaqi_speed.databinding.ActivityAuthBinaBinding

class AuthBinaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinaBinding
    companion object { private const val TAG = "AuthBinaActivity" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAuthLogin.setOnClickListener { doLogin() }
    }

    private fun doLogin() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        // Validasi
        if (username.isEmpty()) {
            binding.tilUsername.error = "Username tidak boleh kosong"
            return
        } else {
            binding.tilUsername.error = null
        }

        if (password.length < 4) {
            binding.tilPassword.error = "Password minimal 4 karakter"
            return
        } else {
            binding.tilPassword.error = null
        }

        Log.i(TAG, "Login berhasil: $username")

        // Simpan ke SharedPreferences
        getSharedPreferences(SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(SplashBinaActivity.KEY_LOGIN, true)
            .putString(SplashBinaActivity.KEY_USER, username)
            .apply()

        Toast.makeText(this, "Selamat datang, $username!", Toast.LENGTH_SHORT).show()

        // Pindah ke Main
        startActivity(Intent(this, MainActivity::class.java).apply {
            putExtra(SplashBinaActivity.KEY_USER, username)
        })
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}