package com.example.weatherappkaterina.ui.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ProgressBarComponent(modifier = Modifier.size(LocalConfiguration.current.screenWidthDp.dp / 3))
            }
        }

        is WeatherState.Success -> {
              (weatherState.value as WeatherState.Success).weather?.let {
                  WeatherComponent(
                      weather = it
                  )
              }
        }

        is WeatherState.Error -> {
            Text(
                textAlign = TextAlign.Center,
                modifier = modifier
                    .width(150.dp)
                    .height(150.dp)
                    .wrapContentHeight(),
                text = "Oops, something went wrong. Please try again later."
            )
        }
    }
}