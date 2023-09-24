package com.example.weatherappkaterina.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappkaterina.core.WeatherResult
import com.example.weatherappkaterina.domain.location.LocationTracker
import com.example.weatherappkaterina.domain.repository.WeatherRepository
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
    private val weatherRepository: WeatherRepository, private val locationTracker: LocationTracker
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState = _weatherState.asStateFlow()

    fun loadLocation() {
        _weatherState.value = WeatherState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val locationData = locationTracker.getCurrentLocation()
                if (locationData != null) {
                    val result = weatherRepository.getWeather(
                        locationData.latitude.toString(), locationData.longitude.toString()
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
                } else {
                    _weatherState.value = WeatherState.Error("Error fetching weather")
                }
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(e.message)
            }
        }
    }
}