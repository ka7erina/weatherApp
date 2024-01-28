package com.example.weatherappkaterina.ui.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.weatherappkaterina.ui.components.ErrorMessageComponent
import com.example.weatherappkaterina.ui.components.ProgressBarComponent
import com.example.weatherappkaterina.ui.components.WeatherComponent

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    weatherViewModel: WeatherViewModel
) {
    val weatherState = weatherViewModel.weatherState.collectAsState()

    when (weatherState.value) {
        is WeatherState.Loading -> {
            ProgressBarComponent()
        }

        is WeatherState.Success -> {
            (weatherState.value as WeatherState.Success).weather?.let {
                WeatherComponent(
                    weather = it
                )
            }
        }

        is WeatherState.Error -> {
            ErrorMessageComponent()
        }
    }
}