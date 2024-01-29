package com.example.weatherappkaterina.domain.model

sealed interface WeatherResult {

    data class Success(val data: WeatherResponse) : WeatherResult
    data class Error(val message: String) : WeatherResult
}