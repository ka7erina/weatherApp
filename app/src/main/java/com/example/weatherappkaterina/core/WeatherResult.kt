package com.example.weatherappkaterina.core

import com.example.weatherappkaterina.domain.model.WeatherResponse

sealed class WeatherResult {

    data class Success(val data: WeatherResponse) : WeatherResult()
    data class Error(val message: String) : WeatherResult()
}