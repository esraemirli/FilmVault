package com.feature.home.data.di

import com.feature.home.data.HomeApiService
import com.feature.home.data.repository.HomePageRepository
import com.feature.home.data.repository.HomePageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): HomeApiService = retrofit.create(HomeApiService::class.java)

    @Provides
    fun provideHomePageRepository(apiService: HomeApiService) : HomePageRepository =
        HomePageRepositoryImpl(apiService)
}