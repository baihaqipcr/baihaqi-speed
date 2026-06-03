package com.example.baihaqi_speed.bantuan_sosial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.baihaqi_speed.MainActivity
import com.example.baihaqi_speed.R
import com.example.baihaqi_speed.bantuan_sosial.tutorial.TutorialMessageActivity
import com.example.baihaqi_speed.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "AboutFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: AboutFragment")

        // Memanggil fungsi inisialisasi komponen UI
        setupListView()
        setupFeedback()
        setupNavigation()

        // Setup Toolbar sesuai ID di XML (toolbarAbout)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbarAbout)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            // Mematikan title default Android agar tidak menabrak TextView kustom di XML kamu
            setDisplayShowTitleEnabled(false)
        }

        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)
    }

    private fun setupListView() {
        val menuItems = arrayOf(
            "Tentang Program Bina Desa",
            "Kebijakan Privasi",
            "Syarat & Ketentuan",
            "Panduan Relawan",
            "Pusat Bantuan",
            "Versi Aplikasi"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            menuItems
        )

        binding.lvAboutMenu.adapter = adapter

        binding.lvAboutMenu.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = menuItems[position]
            Toast.makeText(context, "Membuka: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupFeedback() {
        binding.btnAboutSubmit.setOnClickListener {
            val feedback = binding.etAboutFeedback.text.toString().trim()
            if (feedback.isNotEmpty()) {
                Toast.makeText(context, "Aspirasi Anda telah terkirim. Terima kasih!", Toast.LENGTH_LONG).show()
                binding.etAboutFeedback.text?.clear()
            } else {
                binding.etAboutFeedback.error = "Mohon isi aspirasi Anda"
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupNavigation() {
        // Navigasi ke SettingsFragment lewat icon manage di toolbar
        binding.ivAboutSettings.setOnClickListener {
            (activity as? MainActivity)?.loadFragment(SettingsFragment(), "SettingsFragment")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}