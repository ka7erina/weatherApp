package com.example.weatherappkaterina.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.weatherappkaterina.R
import com.example.weatherappkaterina.domain.model.WeatherResponse

@Composable
fun WeatherComponent(
    modifier: Modifier = Modifier,
    weather: WeatherResponse
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = weather.name, fontSize = 42.sp)
        Text(text = weather.weather[0].description.replaceFirstChar(Char::uppercase))
        Text(text = stringResource(R.string.current_temperature, weather.main.temp))
        Text(text = stringResource(R.string.feels_like, weather.main.feels_like))
        Text(
            text = stringResource(
                R.string.hign_temp_low_temp,
                weather.main.temp_max,
                weather.main.temp_min
            )
        )
    }
}