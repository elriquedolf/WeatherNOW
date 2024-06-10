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