package com.example.weatherappkaterina.ui.weather

import android.location.Location
import com.example.weatherappkaterina.domain.model.Main
import com.example.weatherappkaterina.domain.model.Weather
import com.example.weatherappkaterina.domain.model.WeatherResponse
import com.example.weatherappkaterina.domain.model.WeatherResult
import com.example.weatherappkaterina.domain.usecase.GetLocationUseCase
import com.example.weatherappkaterina.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class WeatherViewModelTest {
    private val getLocationUseCase: GetLocationUseCase = mock()
    private val getWeatherUseCase: GetWeatherUseCase = mock()

    private val viewModel: WeatherViewModel = WeatherViewModel(getLocationUseCase, getWeatherUseCase)

    @Test
    fun `when location and weather information is present then emit success`() = runTest {
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

        viewModel.loadLocation()

        `when`(getLocationUseCase()).thenReturn(location)
        `when`(getWeatherUseCase("${location.latitude}", "${location.longitude}")).thenReturn(flowOf(WeatherResult.Success(data = weather)))

        val state = viewModel.weatherState.value
        assert(state is WeatherState.Success)
    }

    @Test
    fun `when weather information is not present then emit error`() = runTest {
        val location: Location = mock()
        location.latitude = 37.442
        location.longitude = 45.144

        viewModel.loadLocation()

        `when`(getLocationUseCase()).thenReturn(location)
        `when`(getWeatherUseCase("${location.latitude}", "${location.longitude}")).thenReturn(flowOf(WeatherResult.Error(message = "Error")))

        val state = viewModel.weatherState.value
        assert(state is WeatherState.Error)
    }
}