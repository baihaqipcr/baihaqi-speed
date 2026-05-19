package com.example.baihaqi_speed.bantuan_sosial

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.baihaqi_speed.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "SettingsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: SettingsFragment")

        setupListView()
        setupSaveButton()
    }

    private fun setupListView() {
        val accountOptions = arrayOf(
            "Ubah Profil",
            "Ganti Password",
            "Keamanan Akun",
            "Hapus Riwayat"
        )

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            accountOptions
        )

        binding.lvAccountSettings.adapter = adapter

        binding.lvAccountSettings.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(context, "Membuka ${accountOptions[position]}...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupSaveButton() {
        binding.btnSaveSettings.setOnClickListener {
            val isNotifActive = binding.switchNotifications.isChecked
            val isUpdateActive = binding.switchUpdateDesa.isChecked
            
            Log.d(TAG, "Settings Saved: Notif=$isNotifActive, Update=$isUpdateActive")
            Toast.makeText(context, "Pengaturan berhasil disimpan!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
