package com.example.weatherappkaterina.di

import android.app.Application
import com.example.weatherappkaterina.data.location.LocationTrackerImpl
import com.example.weatherappkaterina.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocationBindingModule {

    @Singleton
    @Binds
    fun bindLocationTracker(locationTrackerImpl: LocationTrackerImpl): LocationTracker
}

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }
}