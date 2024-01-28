package com.example.weatherappkaterina.domain.repository

import com.example.weatherappkaterina.domain.model.WeatherResult
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeather(lat: String, lon: String): Flow<WeatherResult>
}