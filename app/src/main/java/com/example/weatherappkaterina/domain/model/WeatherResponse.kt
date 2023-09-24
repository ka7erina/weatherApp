package com.example.weatherappkaterina.domain.model

data class WeatherResponse(
    val main: Main,
    val name: String,
    val weather: List<Weather>
)