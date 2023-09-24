package com.example.weatherappkaterina.data.repository

import com.example.weatherappkaterina.data.api.ApiService
import com.example.weatherappkaterina.core.WeatherResult
import com.example.weatherappkaterina.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    WeatherRepository {

    override suspend fun getWeather(lat: String, lon: String) = flow {
        val response = apiService.getWeather(lat, lon)
        if (response.isSuccessful && response.body() != null) {
            emit(WeatherResult.Success(response.body()!!))
        } else {
            emit(WeatherResult.Error("Error fetching weather"))
        }
    }.catch { e ->
        emit(WeatherResult.Error(e.message.toString()))
    }
}