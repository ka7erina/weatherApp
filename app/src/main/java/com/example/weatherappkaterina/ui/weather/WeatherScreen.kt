package com.example.weatherappkaterina.ui.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.weatherappkaterina.ui.components.ErrorMessageComponent
import com.example.weatherappkaterina.ui.components.ProgressBarComponent
import com.example.weatherappkaterina.ui.components.WeatherComponent

@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel
) {
    when (val state = weatherViewModel.weatherState.collectAsState().value) {
        is WeatherState.Loading -> ProgressBarComponent()
        is WeatherState.Success -> WeatherComponent(weather = state.weather)
        is WeatherState.Error -> ErrorMessageComponent()
    }
}