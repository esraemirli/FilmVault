package com.feature.search.data.di

import com.feature.search.data.SearchApiService
import com.feature.search.data.repository.MovieListRepository
import com.feature.search.data.repository.MovieListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): SearchApiService = retrofit.create(SearchApiService::class.java)

    @Provides
    fun provideHomePageRepository(apiService: SearchApiService) : MovieListRepository =
        MovieListRepositoryImpl(apiService)
}