package com.example.weatherappkaterina.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappkaterina.R
import com.example.weatherappkaterina.domain.model.WeatherData
import com.example.weatherappkaterina.ui.theme.Blue

@Composable
fun WeatherSuccessComponent(
    modifier: Modifier = Modifier,
    weather: WeatherData
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = weather.name, color = Color.White, fontSize = 24.sp)
        Text(text = "${weather.main.temp}", color = Color.White, fontSize = 64.sp)
        weather.weather.forEach {
            Text(modifier = modifier.padding(bottom = 8.dp), text = it.description.replaceFirstChar(Char::uppercase), color = Color.White, fontSize = 24.sp)
        }
        Text(modifier = modifier.padding(bottom = 8.dp), text = stringResource(R.string.feels_like, weather.main.feelsLike), color = Color.White, fontSize = 24.sp)
        Text(
            modifier = modifier.padding(bottom = 8.dp), text = stringResource(
                R.string.hign_temp_low_temp,
                weather.main.tempMax,
                weather.main.tempMin
            ), color = Color.White, fontSize = 24.sp
        )
    }
}