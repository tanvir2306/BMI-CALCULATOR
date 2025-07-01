package com.example.bmicalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Button.setOnClickListener {
            calculateBMI()
        }
    }

    private fun calculateBMI() {

        val weight = binding.edit1.text.toString().toFloatOrNull()
        val height = binding.edit2.text.toString().toFloatOrNull()


        if (weight == null || height == null || weight <= 0.0 || height <= 0.0) {
            Toast.makeText(this, "Please enter valid weight and height values", Toast.LENGTH_SHORT).show()
            return
        }

        val heightInMeters = height / 100

        val bmi = weight / (heightInMeters * heightInMeters)

        binding.Log3.text = String.format("%.2f", bmi)

        val bmiCategory = when {
            bmi < 18.5 -> "Underweight"
            bmi in 18.5..24.9 -> "Normal weight"
            bmi in 25.0..29.9 -> "Overweight"
            else -> "Obese"
        }

        binding.edit3.text = bmiCategory

        val obesityMessage = "(obese range is greater than 29.99)"
        val resultWithMessage = "$bmiCategory $obesityMessage"
        binding.edit3.text = resultWithMessage
    }

}