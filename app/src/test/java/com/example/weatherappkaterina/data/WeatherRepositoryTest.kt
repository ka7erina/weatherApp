package com.example.weatherappkaterina.data

import com.example.weatherappkaterina.data.api.ApiService
import com.example.weatherappkaterina.data.repository.WeatherRepositoryImpl
import com.example.weatherappkaterina.domain.model.WeatherData
import com.example.weatherappkaterina.domain.model.WeatherResult
import java.io.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {

    private val apiService: ApiService = mock()
    private val weatherRepository = WeatherRepositoryImpl(apiService)

    @Test
    fun `when getWeather is called then return success`() = runTest {
        val lat = "37.7749"
        val lon = "-122.4194"
        val response: Response<WeatherData> = mock()
        `when`(apiService.getWeather(lat, lon)).thenReturn(response)

        val expected = weatherRepository.getWeather(lat, lon).first()
        assertEquals(expected, WeatherResult.Success(response.body()!!))
    }

    @Test
    fun `when getWeather is called then return a HTTP error`() = runTest {
        val lat = "37.7749"
        val lon = "-122.4194"
        val httpException: HttpException = mock()
        `when`(httpException.message()).thenReturn("HTTP error")
        `when`(apiService.getWeather(lat, lon)).thenThrow(httpException)

        val expected = weatherRepository.getWeather(lat, lon).first()
        assertEquals(expected, WeatherResult.Error("HTTP error"))
    }

    @Test
    fun `when getWeather is called then return an IO error`() = runTest {
        val lat = "37.7749"
        val lon = "-122.4194"
        `when`(apiService.getWeather(lat, lon)).thenThrow(IOException("IO error"))

        val expected = weatherRepository.getWeather(lat, lon).first()
        assertEquals(expected, WeatherResult.Error("IO error"))
    }
}
