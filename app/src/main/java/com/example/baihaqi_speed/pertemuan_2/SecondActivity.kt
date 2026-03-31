package com.example.baihaqi_speed.pertemuan_2
import com.example.baihaqi_speed.R
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var etAlas: EditText
    private lateinit var etTinggiSegitiga: EditText
    private lateinit var btnSegitiga: Button
    private lateinit var tvHasilSegitiga: TextView

    private lateinit var etPanjang: EditText
    private lateinit var etLebar: EditText
    private lateinit var etTinggiBalok: EditText
    private lateinit var btnBalok: Button
    private lateinit var tvHasilBalok: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // SEGITIGA
        etAlas = findViewById(R.id.etAlas)
        etTinggiSegitiga = findViewById(R.id.etTinggiSegitiga)
        btnSegitiga = findViewById(R.id.btnHitungSegitiga)
        tvHasilSegitiga = findViewById(R.id.tvHasilSegitiga)

        // BALOK
        etPanjang = findViewById(R.id.etPanjang)
        etLebar = findViewById(R.id.etLebar)
        etTinggiBalok = findViewById(R.id.etTinggiBalok)
        btnBalok = findViewById(R.id.btnHitungBalok)
        tvHasilBalok = findViewById(R.id.tvHasilBalok)

        // HITUNG SEGITIGA
        btnSegitiga.setOnClickListener {
            val alas = etAlas.text.toString().toDoubleOrNull()
            val tinggi = etTinggiSegitiga.text.toString().toDoubleOrNull()

            if (alas != null && tinggi != null) {
                val hasil = 0.5 * alas * tinggi
                tvHasilSegitiga.text = "Hasil: $hasil"
            } else {
                Toast.makeText(this, "Input tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }

        // HITUNG BALOK
        btnBalok.setOnClickListener {
            val p = etPanjang.text.toString().toDoubleOrNull()
            val l = etLebar.text.toString().toDoubleOrNull()
            val t = etTinggiBalok.text.toString().toDoubleOrNull()

            if (p != null && l != null && t != null) {
                val hasil = p * l * t
                tvHasilBalok.text = "Hasil: $hasil"
            } else {
                Toast.makeText(this, "Input tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}