package com.example.baihaqi_speed

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.baihaqi_speed.R
import com.example.baihaqi_speed.bantuan_sosial.AboutFragment
import com.example.baihaqi_speed.bantuan_sosial.HomeFragment
import com.example.baihaqi_speed.bantuan_sosial.ProfileFragment
import com.example.baihaqi_speed.bantuan_sosial.SettingsFragment
import com.example.baihaqi_speed.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate: MainActivity started")

        if (savedInstanceState == null) {
            loadFragment(HomeFragment(), "HomeFragment")
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment(), "HomeFragment")
                    true
                }
                R.id.nav_about -> {
                    loadFragment(AboutFragment(), "AboutFragment")
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment(), "ProfileFragment")
                    true
                }
                else -> false
            }
        }
    }

    fun loadFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.fragmentContainer, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }
}
