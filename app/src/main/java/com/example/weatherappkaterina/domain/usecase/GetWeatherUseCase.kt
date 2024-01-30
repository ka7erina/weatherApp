package com.example.weatherappkaterina.domain.usecase

import com.example.weatherappkaterina.domain.model.WeatherResult
import com.example.weatherappkaterina.domain.repository.WeatherRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    operator fun invoke(lat: String, lon: String): Flow<WeatherResult> = weatherRepository.getWeather(lat, lon)
}
