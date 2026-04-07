package com.example.baihaqi_speed

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.databinding.ActivityWelcomeBinding
import com.example.baihaqi_speed.pertemuan_3.LoginActivity

class WelcomeActivity : AppCompatActivity() {

    // ViewBinding untuk activity_welcome.xml
    private lateinit var binding: ActivityWelcomeBinding

    companion object {
        private const val TAG = "WelcomeActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: WelcomeActivity started")

        // Ambil data username yang dikirim dari LoginActivity via Intent
        val username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME) ?: "User"

        Log.i(TAG, "Welcome user: $username")

        // Tampilkan username di halaman welcome menggunakan ViewBinding
        binding.tvUsername.text = username
        binding.tvGreeting.text = "Hello,"
    }
}