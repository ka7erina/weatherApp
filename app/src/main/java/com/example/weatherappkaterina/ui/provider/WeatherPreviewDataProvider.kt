package com.example.weatherappkaterina.ui.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.weatherappkaterina.domain.model.Main
import com.example.weatherappkaterina.domain.model.Weather
import com.example.weatherappkaterina.domain.model.WeatherData
import com.example.weatherappkaterina.ui.weather.WeatherState

class WeatherPreviewDataProvider : PreviewParameterProvider<WeatherState> {
    override val values = sequenceOf(
        WeatherState.Loading,
        WeatherState.Success(
            weather = WeatherData(
                name = "Hamburg", main = Main(
                    feelsLike = 29.0, temp = 27.0, tempMin = 27.0, tempMax = 30.0
                ), weather = listOf(
                    Weather(
                        description = "clear sky"
                    )
                )
            )
        ),
        WeatherState.Error
    )
}
