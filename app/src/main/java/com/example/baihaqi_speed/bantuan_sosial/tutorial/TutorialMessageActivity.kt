package com.example.baihaqi_speed.bantuan_sosial.tutorial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.baihaqi_speed.bina_desa.AuthBinaActivity
import com.example.baihaqi_speed.bina_desa.SplashBinaActivity
import com.example.baihaqi_speed.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem < 2) {
                binding.viewPager.currentItem = currentItem + 1
            } else {
                markTutorialAsSeen()
                navigateToLogin()
            }
        }
    }

    private fun setupViewPager() {
        val adapter = TutorialFragmentAdapter(this)
        binding.viewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    binding.btnNext.text = "Mulai Sekarang"
                } else {
                    binding.btnNext.text = "Lanjutkan"
                }
            }
        })
    }

    private fun markTutorialAsSeen() {
        val pref = getSharedPreferences(SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE)
        pref.edit().putBoolean(SplashBinaActivity.KEY_FIRST_TIME, false).apply()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, AuthBinaActivity::class.java))
        finish()
    }
}
