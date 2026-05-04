package com.example.baihaqi_speed.bantuan_sosial

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.baihaqi_speed.bina_desa.SplashBinaActivity
import com.example.baihaqi_speed.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "HomeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: HomeFragment")

        // Ambil nama dari SharedPreferences
        val pref = requireContext().getSharedPreferences(
            SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE
        )
        val username = pref.getString(SplashBinaActivity.KEY_USER, "Relawan") ?: "Relawan"

        binding.tvWelcomeName.text = "$username"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null   // Hindari memory leak
    }
}