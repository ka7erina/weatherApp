package com.example.weatherappkaterina.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappkaterina.domain.model.WeatherResult
import com.example.weatherappkaterina.domain.usecase.GetLocationUseCase
import com.example.weatherappkaterina.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState = _weatherState.asStateFlow()

    fun loadLocation() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val location = getLocationUseCase()
            location?.let {
                getWeather("${it.latitude}", "${it.longitude}")
            } ?: run {
                _weatherState.value = WeatherState.Error
            }
        } catch (e: Exception) {
            _weatherState.value = WeatherState.Error
        }
    }

    private suspend fun getWeather(lat: String, lon: String) {
        getWeatherUseCase(lat, lon).collect {
            when (it) {
                is WeatherResult.Success -> _weatherState.value = WeatherState.Success(it.data)
                is WeatherResult.Error -> _weatherState.value = WeatherState.Error
            }
        }
    }
}