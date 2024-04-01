package com.example.weatherappkaterina.ui.weather

import com.example.weatherappkaterina.domain.model.WeatherData

sealed interface WeatherState {

    data class Success(val weather: WeatherData) : WeatherState
    object Error : WeatherState
    object Loading : WeatherState
}