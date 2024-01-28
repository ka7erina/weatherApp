package com.example.weatherappkaterina.domain.usecase

import com.example.weatherappkaterina.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: String, lon: String) = weatherRepository.getWeather(lat, lon)
}
