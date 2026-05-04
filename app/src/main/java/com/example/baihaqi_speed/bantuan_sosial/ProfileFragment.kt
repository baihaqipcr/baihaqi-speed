package com.example.baihaqi_speed.bantuan_sosial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.baihaqi_speed.bina_desa.AuthBinaActivity
import com.example.baihaqi_speed.bina_desa.SplashBinaActivity
import com.example.baihaqi_speed.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "ProfileFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ProfileFragment")

        // Ambil nama dari SharedPreferences
        val pref = requireContext().getSharedPreferences(
            SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE
        )
        val username = pref.getString(SplashBinaActivity.KEY_USER, "Pengguna") ?: "Pengguna"
        binding.tvProfileName.text = username

        // Tombol Logout
        binding.btnLogout.setOnClickListener {
            Log.d(TAG, "Tombol logout diklik")
            showLogoutConfirmation()
        }
    }

    private fun showLogoutConfirmation() {
        AlertDialog.Builder(requireContext())
            .setTitle("Konfirmasi Keluar")
            .setMessage("Apakah Anda yakin ingin keluar dari akun?")
            .setPositiveButton("Ya, Keluar") { _, _ ->
                Log.i(TAG, "User logout")
                // Hapus SharedPreferences
                requireContext()
                    .getSharedPreferences(SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply()

                // Kembali ke Auth
                val intent = Intent(requireContext(), AuthBinaActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
                startActivity(intent)
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
                Log.d(TAG, "Logout dibatalkan")
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}