package com.example.weatherappkaterina.data.api

import com.example.weatherappkaterina.domain.model.WeatherResponse
import com.example.weatherappkaterina.core.API_KEY
import com.example.weatherappkaterina.core.UNITS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("weather")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("units") units: String = UNITS,
        @Query("appid") id: String = API_KEY,
        ): Response<WeatherResponse>
}