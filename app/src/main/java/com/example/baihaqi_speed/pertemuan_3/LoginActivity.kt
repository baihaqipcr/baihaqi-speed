package com.example.baihaqi_speed.pertemuan_3

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.HomeActivity
import com.example.baihaqi_speed.bina_desa.RegisterActivity
import com.example.baihaqi_speed.bina_desa.SplashBinaActivity
import com.example.baihaqi_speed.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    companion object {
        private const val TAG = "LoginActivity"
        const val EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        Log.d(TAG, "onCreate: LoginActivity started")

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            validateAndLogin()
        }

        binding.btnGoogle.setOnClickListener {
            Toast.makeText(this, "Google Sign-In coming soon!", Toast.LENGTH_SHORT).show()
        }

        binding.tvNewUser.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateAndLogin() {
        val inputUsername = binding.etUsername.text.toString().trim()
        val inputPassword = binding.etPassword.text.toString().trim()

        // Reset errors
        binding.tilUsername.error = null
        binding.tilPassword.error = null

        var isValid = true

        if (inputUsername.isEmpty()) {
            binding.tilUsername.error = "Username tidak boleh kosong"
            isValid = false
        }

        if (inputPassword.isEmpty()) {
            binding.tilPassword.error = "Password tidak boleh kosong"
            isValid = false
        }

        if (!isValid) return

        // Ambil data dari SharedPreferences (yang disimpan saat Registrasi)
        val pref = getSharedPreferences(SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE)
        val savedUsername = pref.getString(SplashBinaActivity.KEY_USER, null)
        val savedPassword = pref.getString(RegisterActivity.KEY_PASSWORD, null)

        if (savedUsername == null) {
            binding.tilUsername.error = "User belum terdaftar"
            return
        }

        if (inputUsername == savedUsername && inputPassword == savedPassword) {
            // Login Berhasil
            Log.i(TAG, "Login berhasil untuk user: $inputUsername")
            
            // Tandai sudah login di SP
            pref.edit().putBoolean(SplashBinaActivity.KEY_LOGIN, true).apply()
            
            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra(EXTRA_USERNAME, inputUsername)
            }
            startActivity(intent)
            finish()
        } else {
            // Login Gagal
            if (inputUsername != savedUsername) {
                binding.tilUsername.error = "Username salah"
            } else {
                binding.tilPassword.error = "Password salah"
            }
        }
    }
}
