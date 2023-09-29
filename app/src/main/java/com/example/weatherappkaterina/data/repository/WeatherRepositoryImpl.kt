package com.example.weatherappkaterina.data.repository

import com.example.weatherappkaterina.data.api.ApiService
import com.example.weatherappkaterina.core.WeatherResult
import com.example.weatherappkaterina.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WeatherRepository {

    override suspend fun getWeather(lat: String, lon: String) = flow {
        try {
            val response = apiService.getWeather(lat, lon)
            emit(WeatherResult.Success(response.body()))
        } catch (e: Exception) {
            emit(WeatherResult.Error(e.message))
        }
    }
}