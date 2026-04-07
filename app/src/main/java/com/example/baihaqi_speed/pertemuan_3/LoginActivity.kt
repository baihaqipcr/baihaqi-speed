package com.example.baihaqi_speed.pertemuan_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.WelcomeActivity
import com.example.baihaqi_speed.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    // ViewBinding — otomatis generate class dari nama layout (activity_login.xml → ActivityLoginBinding)
    private lateinit var binding: ActivityLoginBinding

    companion object {
        private const val TAG = "LoginActivity"
        // Key untuk passing data antar Activity via Intent
        const val EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding — menggantikan setContentView + findViewById
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        Log.d(TAG, "onCreate: LoginActivity started")

        setupClickListeners()
    }

    private fun setupClickListeners() {

        // Tombol Login utama
        binding.btnLogin.setOnClickListener {
            Log.d(TAG, "btnLogin clicked")
            validateAndLogin()
        }

        // Tombol Google (simulasi)
        binding.btnGoogle.setOnClickListener {
            Toast.makeText(this, "Google Sign-In coming soon!", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "btnGoogle clicked")
        }

        // Link buat akun baru
        binding.tvNewUser.setOnClickListener {
            Toast.makeText(this, "Register page coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateAndLogin() {
        // Ambil input menggunakan ViewBinding (tanpa findViewById!)
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        Log.d(TAG, "Attempting login for: $username")

        // Validasi input kosong
        when {
            username.isEmpty() -> {
                binding.etUsername.error = "Username tidak boleh kosong"
                binding.etUsername.requestFocus()
                Log.w(TAG, "Username kosong")
                return
            }
            password.isEmpty() -> {
                binding.etPassword.error = "Password tidak boleh kosong"
                binding.etPassword.requestFocus()
                Log.w(TAG, "Password kosong")
                return
            }
            password.length < 6 -> {
                binding.etPassword.error = "Password minimal 6 karakter"
                binding.etPassword.requestFocus()
                Log.w(TAG, "Password terlalu pendek")
                return
            }
        }

        // Login berhasil → pindah ke WelcomeActivity
        Log.i(TAG, "Login berhasil untuk user: $username")
        Toast.makeText(this, "Login berhasil! Selamat datang, $username", Toast.LENGTH_SHORT).show()

        // Buat Intent untuk berpindah Activity
        val intent = Intent(this, WelcomeActivity::class.java).apply {
            // Kirim data username ke halaman berikutnya
            putExtra(EXTRA_USERNAME, username)
        }
        startActivity(intent)

        // Tutup LoginActivity agar tidak bisa kembali dengan tombol Back
        finish()
    }
}
