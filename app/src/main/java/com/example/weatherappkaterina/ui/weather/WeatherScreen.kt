package com.example.weatherappkaterina.ui.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.weatherappkaterina.ui.components.WeatherErrorComponent
import com.example.weatherappkaterina.ui.components.LoadingComponent
import com.example.weatherappkaterina.ui.components.WeatherSuccessComponent
import com.example.weatherappkaterina.ui.provider.WeatherPreviewDataProvider

@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel
) {
    Weather(state = weatherViewModel.weatherState.collectAsState().value)
}

@Composable
fun Weather(state: WeatherState) {
    when (state) {
        is WeatherState.Loading -> LoadingComponent()
        is WeatherState.Success -> WeatherSuccessComponent(weather = state.weather)
        is WeatherState.Error -> WeatherErrorComponent()
    }
}

@Preview(showBackground = true)
@Composable
private fun WeatherPreview(
    @PreviewParameter(WeatherPreviewDataProvider::class) state: WeatherState
) {
    Weather(state = state)
}

