Elrique Dolf
ST10434187
https://github.com/elriquedolf/WeatherNOW.git

Discription:
Weather NOW allows users to input the day, min weather, max weather, and notes regarding weather conditions, then provides the average min, max and total weather for the week but also allows users to view detailed information for each app with a click of a few buttons.

Pseudocode

Splash screen

package com.example.weathernow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_splash`)
// Declarations
        val startButton = findViewById<Button>(R.id.startButton)
        val exitButton = findViewById<Button>(R.id.exitButton)
//Intent to go to the main screen from the splash screen
        startButton.setOnClickListener {
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }
        exitButton.setOnClickListener {
            finish()
        }
    }
}

Main screen 

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

Detailed Views screen

package com.example.weathernow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DetailedActivity : AppCompatActivity() {
// Declarations
    private lateinit var weatherdetailsTextView: TextView
    private lateinit var averageTextView: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)
// Initializations
        weatherdetailsTextView = findViewById(R.id.weatherdetailsTextView)
        averageTextView = findViewById(R.id.averageTextView)
        backButton = findViewById(R.id.backButton)

        val minMax = intent.getIntArrayExtra("minMax") as ArrayList<Pair<Int, Int>>
        val weatherconditionsInput = intent.getIntArrayExtra("weatherconditionsInput") as ArrayList<String>

        displayDetails(minMax, weatherconditionsInput)
        calculateAndDisplayAverage(minMax)
//Button that goes back to the main screen
        backButton.setOnClickListener {
            finish()
        }
    }
// Displays detailed information that the user had input
    private fun displayDetails(minMax: List<Pair<Int, Int>>, weatherconsitionsInput: List<String>) {
        val details = StringBuilder()
        for (i in minMax.indices) {
            details.append("Day ${i + 1} - Min: ${minMax[i].first} min, Max: ${minMax[i].second} min\n")
            details.append("Weather conditions: ${weatherconsitionsInput[i]}\n\n")
        }
        weatherdetailsTextView.text = details.toString()
    }
// Calculates average
    private fun calculateAndDisplayAverage(minMax: List<Pair<Int, Int>>) {
        var totalMinWeather = 0
        var totalMaxWeather= 0

        for (weather in minMax) {
            totalMinWeather += weather.first
            totalMaxWeather += weather.second
        }

        val averageMinWeather = totalMinWeather / minMax.size
        val averageMaxWeather = totalMinWeather / minMax.size
        val averageTotalWeather = (totalMinWeather + totalMaxWeather) / minMax.size

        averageTextView.text = "Average Min weather: $averageMinWeather min\n" +
                "Average Max weather: $averageMaxWeather min\n" +
                "Average Total weather: $averageTotalWeather min"
    }
}

Screenshots 
![Screenshot (2407)](https://github.com/elriquedolf/WeatherNOW/assets/163988160/a0085e11-f382-47d3-bdb6-bcb88bcc190c)
![Screenshot (2403)](https://github.com/elriquedolf/WeatherNOW/assets/163988160/e51a6120-77a3-45c4-afaa-1ae86b6cf782)
![Screenshot (2404)](https://github.com/elriquedolf/WeatherNOW/assets/163988160/c27f61d5-438d-4912-891a-e4ddd625f3fd)
![Screenshot (2405)](https://github.com/elriquedolf/WeatherNOW/assets/163988160/24558160-9492-49c9-b7b9-b1644cb66a0d)

// Reference list
// IIE. 2024. Introduction to mobile app and web development. Module Manuel. The independent institution of education

