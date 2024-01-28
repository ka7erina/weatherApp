package com.example.weatherappkaterina.di

import com.example.weatherappkaterina.data.repository.WeatherRepositoryImpl
import com.example.weatherappkaterina.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface WeatherRepositoryBindingModule {

    @Binds
    @Singleton
    fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}

