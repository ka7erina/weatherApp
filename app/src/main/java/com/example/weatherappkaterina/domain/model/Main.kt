package com.example.weatherappkaterina.domain.model

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double = 0.0,
    val temp: Double = 0.0,
    @SerializedName("temp_max")
    val tempMax: Double = 0.0,
    @SerializedName("temp_max")
    val tempMin: Double = 0.0
)