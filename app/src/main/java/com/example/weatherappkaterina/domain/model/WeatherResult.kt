package com.example.weatherappkaterina.domain.model

sealed interface WeatherResult {

    data class Success(val data: WeatherData) : WeatherResult
    data class Error(val message: String) : WeatherResult
}