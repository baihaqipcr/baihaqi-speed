package com.example.baihaqi_speed

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.baihaqi_speed.R
import com.example.baihaqi_speed.bantuan_sosial.AboutFragment
import com.example.baihaqi_speed.bantuan_sosial.HomeFragment
import com.example.baihaqi_speed.bantuan_sosial.ProfileFragment
import com.example.baihaqi_speed.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi ViewBinding sesuai dengan activity_main.xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: MainActivity started")

        // Tampilkan fragment awal (Home) jika pertama kali dibuka
        if (savedInstanceState == null) {
            loadFragment(HomeFragment(), "HomeFragment")
        }

        // Setup BottomNavigation listener
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Log.d(TAG, "Navigasi ke: Home")
                    loadFragment(HomeFragment(), "HomeFragment")
                    true
                }
                R.id.nav_about -> {
                    Log.d(TAG, "Navigasi ke: About")
                    loadFragment(AboutFragment(), "AboutFragment")
                    true
                }
                R.id.nav_profile -> {
                    Log.d(TAG, "Navigasi ke: Profile")
                    loadFragment(ProfileFragment(), "ProfileFragment")
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.fragmentContainer, fragment, tag)
            .commit()
    }
}