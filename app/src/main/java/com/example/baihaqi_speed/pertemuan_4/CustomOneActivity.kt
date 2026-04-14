package com.example.baihaqi_speed

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.databinding.ActivityCustomOneBinding

class CustomOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomOneBinding
    companion object { private const val TAG = "CustomOneActivity" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeTitle = intent.getStringExtra(HomeActivity.EXTRA_HOME_TITLE) ?: ""
        val homeDesc  = intent.getStringExtra(HomeActivity.EXTRA_HOME_DESC) ?: ""

        binding.tvFromHomeTitle.text = homeTitle
        binding.tvFromHomeDesc.text  = homeDesc
        binding.tvPageTitle.text     = "Galeri Bentuk"

        Log.d(TAG, "CustomOne → $homeTitle | $homeDesc")

        binding.btnBack.setOnClickListener { finish() }
    }
}