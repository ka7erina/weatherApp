package com.example.weatherappkaterina.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.weatherappkaterina.R
import com.example.weatherappkaterina.domain.model.Main
import com.example.weatherappkaterina.domain.model.Weather
import com.example.weatherappkaterina.domain.model.WeatherData

@Composable
fun WeatherComponent(
    modifier: Modifier = Modifier,
    weather: WeatherData
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = weather.name, fontSize = 42.sp)
        Text(text = weather.weather[0].description.replaceFirstChar(Char::uppercase))
        Text(text = stringResource(R.string.current_temperature, weather.main.temp))
        Text(text = stringResource(R.string.feels_like, weather.main.feelsLike))
        Text(
            text = stringResource(
                R.string.hign_temp_low_temp,
                weather.main.tempMax,
                weather.main.tempMin
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherComponentPreview() {
    WeatherComponent(
        weather = WeatherData(
            main = Main(),
            name = "Name",
            weather = listOf(Weather(description = "description"))
        )
    )
}