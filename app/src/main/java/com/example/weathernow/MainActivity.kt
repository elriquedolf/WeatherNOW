package com.example.weathernow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
// Declarations
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button
    private lateinit var detailsButton: Button
    private lateinit var dayEditText: EditText
    private lateinit var minEditText: EditText
    private lateinit var maxEditText: EditText
    private lateinit var weatherEditText: EditText

    private var minMax = mutableListOf<Pair<Int, Int>>()
    private var weatherConditions = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// Initialization
        saveButton = findViewById(R.id.saveButton)
        clearButton = findViewById(R.id.clearButton)
        detailsButton = findViewById(R.id.detailedviewButton)
        dayEditText = findViewById(R.id.dayEditText)
        minEditText = findViewById(R.id.minEditText)
        maxEditText = findViewById(R.id.maxEditText)
        weatherEditText = findViewById(R.id.weatherEditText)

        saveButton.setOnClickListener {
            save()
        }

        clearButton.setOnClickListener {
            clear()
        }

        detailsButton.setOnClickListener {
            val intent = Intent(this, DetailedActivity::class.java)
            intent.putExtra("minMax", ArrayList(minMax))
            intent.putExtra("weatherConditions", ArrayList(weatherConditions))
            startActivity(intent)
        }
    }
// Butoon that saves user input
    private fun save() {
        val minInput = minEditText.text.toString().toIntOrNull()
        val maxInput = maxEditText.text.toString().toIntOrNull()
        val weatherconditionsInput = weatherEditText.text.toString()

        if (minInput != null && maxInput != null) {
            minMax.add(Pair(minInput, maxInput))
            weatherConditions.add(weatherconditionsInput)
            Toast.makeText(this, "Succsessfully Saved", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Invalid Input. Please enter valid number", Toast.LENGTH_SHORT)
                .show()
        }
    }
// Button that clears users inputs
    private fun clear() {
        minMax.clear()
        weatherConditions.clear()
        Toast.makeText(this, "Successfully cleared", Toast.LENGTH_SHORT).show()
    }
}