package com.example.baihaqi_speed.bantuan_sosial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.baihaqi_speed.databinding.FragmentProgramsBinding
import com.google.android.material.tabs.TabLayout

class ProgramsFragment : Fragment() {

    private var _binding: FragmentProgramsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProgramAdapter
    
    // Data Dummy Bina Desa menggunakan ProgramsModel (20 Item)
    private val allPrograms = listOf(
        ProgramsModel("Paket Sembako Premium", "Rp 150.000", "Bantuan beras, minyak goreng, dan gula untuk keluarga.", "https://picsum.photos/seed/rice/400/300", "Sembako"),
        ProgramsModel("Cek Kesehatan Lansia", "Gratis", "Pemeriksaan gula darah dan tekanan darah rutin.", "https://picsum.photos/seed/health/400/300", "Kesehatan"),
        ProgramsModel("Beasiswa Pendidikan", "Rp 1.000.000", "Tunjangan buku dan seragam untuk siswa berprestasi.", "https://picsum.photos/seed/school/400/300", "Pendidikan"),
        ProgramsModel("Bantuan Alat Tulis", "Rp 75.000", "Paket lengkap alat tulis untuk anak sekolah dasar.", "https://picsum.photos/seed/book/400/300", "Pendidikan"),
        ProgramsModel("Minyak Goreng Subsidi", "Rp 14.000", "Program tebus murah minyak goreng berkualitas.", "https://picsum.photos/seed/oil/400/300", "Sembako"),
        ProgramsModel("Vaksinasi Booster", "Gratis", "Layanan vaksinasi covid-19 dosis ketiga.", "https://picsum.photos/seed/vaccine/400/300", "Kesehatan"),
        ProgramsModel("Susu Pertumbuhan Anak", "Rp 50.000", "Pencegahan stunting dengan asupan susu bergizi.", "https://picsum.photos/seed/milk/400/300", "Kesehatan"),
        ProgramsModel("Pupuk Organik Cair", "Rp 25.000", "Bantuan untuk petani desa meningkatkan hasil panen.", "https://picsum.photos/seed/plant/400/300", "Sembako"),
        ProgramsModel("Kursus Komputer Desa", "Gratis", "Pelatihan dasar IT untuk pemuda putus sekolah.", "https://picsum.photos/seed/tech/400/300", "Pendidikan"),
        ProgramsModel("Sembako Telur & Ayam", "Rp 100.000", "Paket protein hewani untuk gizi seimbang.", "https://picsum.photos/seed/food/400/300", "Sembako"),
        
        ProgramsModel("Vitamin Anak Ceria", "Gratis", "Suplemen daya tahan tubuh untuk balita desa.", "https://picsum.photos/seed/vitamin/400/300", "Kesehatan"),
        ProgramsModel("Renovasi Kelas PAUD", "Rp 5.000.000", "Perbaikan fasilitas belajar mengajar anak usia dini.", "https://picsum.photos/seed/classroom/400/300", "Pendidikan"),
        ProgramsModel("Bibit Sayuran Unggul", "Rp 10.000", "Bibit cabai, tomat, dan bayam untuk kebun warga.", "https://picsum.photos/seed/seeds/400/300", "Sembako"),
        ProgramsModel("Layanan Ambulans Desa", "Gratis", "Siap siaga 24 jam untuk keadaan darurat warga.", "https://picsum.photos/seed/ambulance/400/300", "Kesehatan"),
        ProgramsModel("Paket Seragam Gratis", "Rp 200.000", "Bantuan pakaian sekolah bagi yang kurang mampu.", "https://picsum.photos/seed/uniform/400/300", "Pendidikan"),
        ProgramsModel("Daging Kurban Kaleng", "Gratis", "Distribusi daging olahan yang tahan lama.", "https://picsum.photos/seed/meat/400/300", "Sembako"),
        ProgramsModel("Alat Bantu Dengar", "Rp 500.000", "Bantuan subsidi untuk warga dengan disabilitas.", "https://picsum.photos/seed/hearing/400/300", "Kesehatan"),
        ProgramsModel("Internet Desa Pintar", "Rp 30.000", "Akses WiFi murah untuk area fasilitas umum.", "https://picsum.photos/seed/wifi/400/300", "Pendidikan"),
        ProgramsModel("Garam Beryodium", "Rp 2.000", "Penyediaan garam sehat untuk konsumsi harian.", "https://picsum.photos/seed/salt/400/300", "Sembako"),
        ProgramsModel("Konsultasi Psikologi", "Gratis", "Layanan kesehatan mental untuk warga terdampak bencana.", "https://picsum.photos/seed/therapy/400/300", "Kesehatan")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupTabLayout()
    }

    private fun setupRecyclerView() {
        // Hubungkan dengan ProgramAdapter
        adapter = ProgramAdapter(allPrograms) { program ->
            Toast.makeText(requireContext(), "Membuka program: ${program.name}", Toast.LENGTH_SHORT).show()
        }
        binding.rvPrograms.adapter = adapter
    }

    private fun setupTabLayout() {
        // Setup Tab kategori filter
        val categories = listOf("Semua", "Sembako", "Kesehatan", "Pendidikan")
        categories.forEach {
            binding.tabLayoutPrograms.addTab(binding.tabLayoutPrograms.newTab().setText(it))
        }

        binding.tabLayoutPrograms.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val category = tab?.text.toString()
                filterPrograms(category)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun filterPrograms(category: String) {
        if (category == "Semua") {
            adapter.updateData(allPrograms)
        } else {
            val filtered = allPrograms.filter { it.category == category }
            adapter.updateData(filtered)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
