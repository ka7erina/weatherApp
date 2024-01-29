package com.example.weatherappkaterina.domain.usecase

import android.location.Location
import com.example.weatherappkaterina.domain.model.Main
import com.example.weatherappkaterina.domain.model.Weather
import com.example.weatherappkaterina.domain.model.WeatherResponse
import com.example.weatherappkaterina.domain.model.WeatherResult
import com.example.weatherappkaterina.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


class GetWeatherUseCaseTest {
    private val weatherRepository: WeatherRepository = mock()

    private val useCase: GetWeatherUseCase = mock()

    @Test
    fun `when repository returns data then use case returns success`() = runTest {
        val location: Location = mock()
        location.latitude = 37.442
        location.longitude = 45.144
        val weather = WeatherResponse(
            name = "Mountain View", main = Main(
                feelsLike = 298.74, temp = 272.1, tempMin = 297.56, tempMax = 300.05
            ), weather = listOf(
                Weather(
                    description = "clear sky"
                )
            )
        )
        `when`(weatherRepository.getWeather("${location.latitude}", "${location.longitude}"))
            .thenReturn(flowOf(WeatherResult.Success(weather)))

        val expected = useCase("${location.latitude}", "${location.longitude}")
        val actual = flowOf(WeatherResult.Success(weather))

        assertEquals(expected, actual)
    }
}