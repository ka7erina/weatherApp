package com.example.weatherappkaterina.domain.model

data class WeatherData(
    val main: Main,
    val name: String,
    val weather: List<Weather>
)