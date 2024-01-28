package com.example.weatherappkaterina.ui.weather

import com.example.weatherappkaterina.domain.model.WeatherResponse

sealed interface WeatherState {

    data class Success(val weather: WeatherResponse?) : WeatherState
    object Error : WeatherState
    object Loading : WeatherState
}