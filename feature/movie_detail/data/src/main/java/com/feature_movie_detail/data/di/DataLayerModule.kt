package com.feature_movie_detail.data.di

import com.feature_movie_detail.data.MovieDetailApiService
import com.feature_movie_detail.data.repository.MovieDetailRepository
import com.feature_movie_detail.data.repository.MovieDetailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): MovieDetailApiService = retrofit.create(MovieDetailApiService::class.java)

    @Provides
    fun provideHomePageRepository(apiService: MovieDetailApiService) : MovieDetailRepository =
        MovieDetailRepositoryImpl(apiService)
}