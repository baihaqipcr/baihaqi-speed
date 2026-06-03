package com.example.baihaqi_speed.bantuan_sosial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.baihaqi_speed.MainActivity
import com.example.baihaqi_speed.bina_desa.DetailBinaActivity
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

        setupHeader()
        setupChips()
        setupSearch()
        setupClickListeners()
    }

    private fun setupHeader() {
        val pref = requireContext().getSharedPreferences(
            SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE
        )
        val username = pref.getString(SplashBinaActivity.KEY_USER, "Relawan") ?: "Relawan"
        binding.tvWelcomeName.text = username
    }

    private fun setupChips() {
        binding.chipGroupCategories.setOnCheckedStateChangeListener { _, checkedIds ->
            val chipName = when (checkedIds.firstOrNull()) {
                binding.chipSemua.id -> "Semua"
                binding.chipSembako.id -> "Sembako"
                binding.chipKesehatan.id -> "Kesehatan"
                binding.chipPendidikan.id -> "Pendidikan"
                else -> "None"
            }
            if (chipName != "None") {
                Toast.makeText(context, "Filter: $chipName", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSearch() {
        binding.tilSearch.setEndIconOnClickListener {
            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()) {
                Toast.makeText(context, "Mencari bantuan: $query", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupClickListeners() {
        // Navigasi ke SettingsFragment
        binding.ivHomeSettings.setOnClickListener {
            (activity as? MainActivity)?.loadFragment(SettingsFragment(), "SettingsFragment")
        }

        binding.btnExploreMore.setOnClickListener {
            Toast.makeText(context, "Menampilkan semua program...", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), DetailBinaActivity::class.java).apply {
                putExtra("extra_program_title", "Bansos Sembako")
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
