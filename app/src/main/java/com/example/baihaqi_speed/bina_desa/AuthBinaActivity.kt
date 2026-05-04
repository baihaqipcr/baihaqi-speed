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

        // Tombol Login
        binding.btnAuthLogin.setOnClickListener { doLogin() }

        // Navigasi ke Halaman Register
        binding.tvToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun doLogin() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        // Validasi input tidak boleh kosong
        var hasError = false
        if (username.isEmpty()) {
            binding.tilUsername.error = "Username tidak boleh kosong"
            hasError = true
        } else {
            binding.tilUsername.error = null
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password tidak boleh kosong"
            hasError = true
        } else {
            binding.tilPassword.error = null
        }

        if (hasError) return

        // Ambil data dari SharedPreferences
        val pref = getSharedPreferences(SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE)
        val savedUser = pref.getString(SplashBinaActivity.KEY_USER, null)
        val savedPass = pref.getString(RegisterActivity.KEY_PASSWORD, null)

        // LOGIC LOGIN SESUAI KETENTUAN:
        // Rule 1: Jika username = password -> Boleh Login
        // Rule 2: Jika username & password sesuai data di SharedPreference -> Boleh Login
        if (username == password) {
            performLogin(username)
        } else if (savedUser != null && username == savedUser && password == savedPass) {
            performLogin(username)
        } else {
            binding.tilUsername.error = "Username atau Password salah"
            Toast.makeText(this, "Login Gagal. Akun tidak ditemukan atau password salah.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun performLogin(username: String) {
        Log.i(TAG, "Login berhasil: $username")

        // Simpan status login
        getSharedPreferences(SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(SplashBinaActivity.KEY_LOGIN, true)
            .putString(SplashBinaActivity.KEY_USER, username)
            .apply()

        Toast.makeText(this, "Selamat datang, $username!", Toast.LENGTH_SHORT).show()

        // Pindah ke MainActivity
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(SplashBinaActivity.KEY_USER, username)
        }
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }
}
