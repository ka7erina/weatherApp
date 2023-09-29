package com.example.weatherappkaterina.ui.weather

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappkaterina.R
import com.example.weatherappkaterina.core.WeatherResult
import com.example.weatherappkaterina.domain.repository.WeatherRepository
import com.example.weatherappkaterina.domain.usecase.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState = _weatherState.asStateFlow()

    fun loadLocation() {
        _weatherState.value = WeatherState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val locationData = getLocationUseCase.getLocation()
                if (locationData != null) {
                    getWeather(
                        locationData.latitude.toString(),
                        locationData.longitude.toString()
                    )
                } else {
                    _weatherState.value = WeatherState.Error(
                        Resources.getSystem().getString(R.string.error_fetching_weather)
                    )
                }
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(e.message)
            }
        }
    }

    private suspend fun getWeather(lat: String, lon: String) {
        val result = weatherRepository.getWeather(
            lat, lon
        )
        result.collectLatest {
            when (it) {
                is WeatherResult.Success -> {
                    _weatherState.value = WeatherState.Success(it.data)
                }

                is WeatherResult.Error -> {
                    _weatherState.value = WeatherState.Error(it.message)
                }
            }
        }
    }
}