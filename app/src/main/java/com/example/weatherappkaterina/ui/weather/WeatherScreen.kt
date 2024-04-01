package com.example.weatherappkaterina.ui.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.weatherappkaterina.ui.components.WeatherErrorComponent
import com.example.weatherappkaterina.ui.components.LoadingComponent
import com.example.weatherappkaterina.ui.components.WeatherSuccessComponent

@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel
) {
    when (val state = weatherViewModel.weatherState.collectAsState().value) {
        is WeatherState.Loading -> LoadingComponent()
        is WeatherState.Success -> WeatherSuccessComponent(weather = state.weather)
        is WeatherState.Error -> WeatherErrorComponent()
    }
}