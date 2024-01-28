package com.example.weatherappkaterina.domain.model

sealed class WeatherResult {

    data class Success(val data: WeatherResponse) : WeatherResult()
    data class Error(val message: String) : WeatherResult()
}