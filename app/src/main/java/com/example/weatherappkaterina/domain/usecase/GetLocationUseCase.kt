package com.example.weatherappkaterina.domain.usecase

import com.example.weatherappkaterina.domain.location.LocationTracker
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val locationTracker: LocationTracker) {
    suspend operator fun invoke() = locationTracker.getCurrentLocation()
}