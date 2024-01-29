package com.example.weatherappkaterina.data.repository

import com.example.weatherappkaterina.data.api.ApiService
import com.example.weatherappkaterina.domain.model.WeatherResult
import com.example.weatherappkaterina.domain.repository.WeatherRepository
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WeatherRepository {

    override suspend fun getWeather(lat: String, lon: String) = flow {
        try {
            val response = apiService.getWeather(lat, lon)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) emit(WeatherResult.Success(response.body()!!))
            }
        } catch (e: HttpException) {
            emit(WeatherResult.Error(e.message ?: "An unexpected error occurred. Please try again later"))
        } catch (e: IOException) {
            emit(WeatherResult.Error(e.message ?: "Couldn't reach server. Check your internet connection"))
        }
    }
}