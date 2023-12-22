package com.feature.home.domain.di

import com.feature.home.data.repository.HomePageRepository
import com.feature.home.domain.mapper.HomePageMapper
import com.feature.home.domain.usecase.GetMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainLayerModule {

    @Provides
    fun provideGetMovieListUseCase(
        repository: HomePageRepository,
        mapper: HomePageMapper
    ): GetMovieListUseCase =
        GetMovieListUseCase(repository, mapper)

}