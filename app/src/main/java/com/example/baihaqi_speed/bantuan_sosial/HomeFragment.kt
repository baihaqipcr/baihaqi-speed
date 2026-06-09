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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baihaqi_speed.MainActivity
import com.example.baihaqi_speed.bantuan_sosial.photo.PhotoAdapter
import com.example.baihaqi_speed.bantuan_sosial.photo.PhotoModel
import com.example.baihaqi_speed.bantuan_sosial.photo.PhotoRepository
import com.example.baihaqi_speed.bina_desa.DetailBinaActivity
import com.example.baihaqi_speed.bina_desa.SplashBinaActivity
import com.example.baihaqi_speed.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

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
        setupRecyclerView()
        fetchPhotos() // Default: semua
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
            val category = when (checkedIds.firstOrNull()) {
                binding.chipSemua.id -> "semua"
                binding.chipSembako.id -> "sembako"
                binding.chipKesehatan.id -> "kesehatan"
                binding.chipPendidikan.id -> "pendidikan"
                else -> "semua"
            }
            fetchPhotos(category)
        }
    }

    private fun setupSearch() {
        binding.tilSearch.setEndIconOnClickListener {
            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()) {
                Toast.makeText(context, "Mencari bantuan: $query", Toast.LENGTH_SHORT).show()
                fetchPhotos(query) // Bisa juga mencari berdasarkan query custom
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvGallery.isNestedScrollingEnabled = true
    }

    private fun fetchPhotos(category: String = "semua") {
        val listProgramDesa = listOf(
            "Pemberdayaan UMKM Desa",
            "Literasi Digital Remaja",
            "Pelatihan Pertanian Organik",
            "Posyandu Balita Sehat",
            "Pembangunan Irigasi Desa"
        )

        val repository = PhotoRepository()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val result = repository.getPhotosByCategory(category)
                result.onSuccess { photos ->
                    // Mapping UnsplashPhoto ke PhotoModel agar sesuai dengan Adapter
                    val mappedPhotos = photos.take(5).mapIndexed { index, photo ->
                        PhotoModel(
                            id = photo.id,
                            title = listProgramDesa.getOrElse(index) { photo.description ?: "Program Bina Desa" },
                            location = photo.user.location ?: "Kabupaten Sleman, DIY",
                            description = photo.description ?: "Program pemberdayaan masyarakat.",
                            imageUrl = photo.urls.regular
                        )
                    }

                    if (mappedPhotos.isNotEmpty()) {
                        binding.rvGallery.adapter = PhotoAdapter(mappedPhotos)
                    } else {
                        Log.e(TAG, "fetchPhotos: Data API kosong")
                    }
                }.onFailure { e ->
                    Log.e(TAG, "fetchPhotos: Error ${e.message}")
                    Toast.makeText(requireContext(), "Gagal memuat galeri", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "fetchPhotos: Exception ${e.message}")
                Toast.makeText(requireContext(), "Gagal memuat galeri dari server", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupClickListeners() {
        binding.ivHomeSettings.setOnClickListener {
            (activity as? MainActivity)?.loadFragment(SettingsFragment(), "SettingsFragment")
        }

        binding.btnExploreMore.setOnClickListener {
            startActivity(Intent(requireContext(), DetailBinaActivity::class.java).apply {
                putExtra("extra_program_title", "Bina Desa")
            })
        }

        binding.btnRefresh.setOnClickListener {
            fetchPhotos()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}