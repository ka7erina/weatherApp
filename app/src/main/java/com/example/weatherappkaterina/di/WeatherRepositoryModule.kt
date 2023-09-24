package com.example.weatherappkaterina.di

import com.example.weatherappkaterina.data.api.ApiService
import com.example.weatherappkaterina.domain.repository.WeatherRepository
import com.example.weatherappkaterina.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface WeatherRepositoryBindingModule {

    @Binds
    @Singleton
    fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}

@InstallIn(SingletonComponent::class)
@Module
object WeatherRepositoryModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}

