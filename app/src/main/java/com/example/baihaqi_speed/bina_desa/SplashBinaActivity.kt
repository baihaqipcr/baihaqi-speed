package com.example.baihaqi_speed.bina_desa

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.MainActivity
import com.example.baihaqi_speed.R
import com.example.baihaqi_speed.databinding.ActivitySplashBinaBinding

@SuppressLint("CustomSplashScreen")
class SplashBinaActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinaBinding
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        const val PREF_NAME   = "bina_desa_pref"
        const val KEY_LOGIN   = "isLogin"
        const val KEY_USER    = "username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Full screen
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )

        binding = ActivitySplashBinaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cek SharedPreferences — sudah login atau belum
        val pref     = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val isLogin  = pref.getBoolean(KEY_LOGIN, false)

        playIntroAnimations()

        // Tombol Jelajahi hanya muncul setelah animasi selesai
        binding.btnSplashExplore.setOnClickListener {
            if (isLogin) {
                goToMain()
            } else {
                goToAuth()
            }
        }

        // Auto-navigate setelah 3 detik jika sudah login
        if (isLogin) {
            handler.postDelayed({ goToMain() }, 3000)
        }
    }

    private fun playIntroAnimations() {
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.splash_title_slide)
        val fadeIn  = AnimationUtils.loadAnimation(this, R.anim.splash_tagline_fade)

        handler.postDelayed({
            binding.tvSplashTitle.animate().alpha(1f).setDuration(700).start()
            binding.tvSplashTitle.startAnimation(slideUp)
        }, 300)

        handler.postDelayed({
            binding.tvSplashSub.animate().alpha(1f).setDuration(500).start()
        }, 200)

        handler.postDelayed({
            binding.tvSplashDesc.animate().alpha(1f).setDuration(600).start()
            binding.tvSplashDesc.startAnimation(fadeIn)
        }, 700)

        handler.postDelayed({
            binding.btnSplashExplore.animate().alpha(1f).setDuration(500).start()
        }, 1000)
    }

    private fun goToAuth() {
        startActivity(Intent(this, AuthBinaActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}