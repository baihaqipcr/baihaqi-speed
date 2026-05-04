package com.example.baihaqi_speed.bina_desa

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baihaqi_speed.databinding.ActivityRegisterBinding
import java.util.Calendar
import java.util.Locale

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    companion object {
        const val KEY_NAME = "reg_name"
        const val KEY_BIRTHDATE = "reg_birthdate"
        const val KEY_GENDER = "reg_gender"
        const val KEY_RELIGION = "reg_religion"
        const val KEY_PASSWORD = "reg_password"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDropdown()
        setupDatePicker()
        setupClickListeners()
    }

    private fun setupDropdown() {
        val religions = arrayOf("Islam", "Kristen Protestan", "Katolik", "Hindu", "Buddha", "Khonghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, religions)
        binding.actvReligion.setAdapter(adapter)
    }

    private fun setupDatePicker() {
        binding.etBirthDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val date = String.format(Locale.getDefault(), "%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear)
                    binding.etBirthDate.setText(date)
                    binding.tilBirthDate.error = null
                }, year, month, day)
            datePickerDialog.show()
        }
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener { validateAndRegister() }
        binding.tvBackToLogin.setOnClickListener { finish() }
    }

    private fun validateAndRegister() {
        val name = binding.etName.text.toString().trim()
        val birthDate = binding.etBirthDate.text.toString().trim()
        val religion = binding.actvReligion.text.toString().trim()
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()

        val selectedGenderId = binding.rgGender.checkedRadioButtonId
        val gender = when (selectedGenderId) {
            binding.rbMale.id -> "Laki-laki"
            binding.rbFemale.id -> "Perempuan"
            else -> ""
        }

        // Reset Errors
        binding.tilName.error = null
        binding.tilBirthDate.error = null
        binding.tilUsername.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null

        if (name.isEmpty() || birthDate.isEmpty() || gender.isEmpty() || religion.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Harap lengkapi semua data", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            binding.tilConfirmPassword.error = "Password tidak cocok"
            return
        }

        // SIMPAN DATA KE SHAREDPREFERENCES
        val pref = getSharedPreferences(SplashBinaActivity.PREF_NAME, Context.MODE_PRIVATE)
        pref.edit().apply {
            putString(KEY_NAME, name)
            putString(KEY_BIRTHDATE, birthDate)
            putString(KEY_GENDER, gender)
            putString(KEY_RELIGION, religion)
            putString(SplashBinaActivity.KEY_USER, username)
            putString(KEY_PASSWORD, password)
            apply()
        }

        Toast.makeText(this, "Registrasi Berhasil! Silakan Login.", Toast.LENGTH_SHORT).show()
        finish()
    }
}
