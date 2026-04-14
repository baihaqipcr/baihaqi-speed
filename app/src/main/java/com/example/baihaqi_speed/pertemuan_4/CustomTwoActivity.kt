package com.example.baihaqi_speed

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.databinding.ActivityCustomTwoBinding

class CustomTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomTwoBinding
    companion object { private const val TAG = "CustomTwoActivity" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeTitle = intent.getStringExtra(HomeActivity.EXTRA_HOME_TITLE) ?: ""
        val homeDesc  = intent.getStringExtra(HomeActivity.EXTRA_HOME_DESC) ?: ""

        binding.tvFromHomeTitle.text = homeTitle
        binding.tvFromHomeDesc.text  = homeDesc
        binding.tvPageTitle.text     = "Tips Penggunaan"

        Log.d(TAG, "CustomTwo → $homeTitle | $homeDesc")

        binding.btnBack.setOnClickListener { finish() }
    }
}