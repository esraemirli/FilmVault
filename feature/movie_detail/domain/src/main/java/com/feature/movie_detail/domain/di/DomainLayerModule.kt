package com.feature.movie_detail.domain.di

import com.feature.movie_detail.domain.usecase.GetMovieDetailUseCase
import com.feature.movie_detail.domain.mapper.MovieDetailMapper
import com.feature_movie_detail.data.repository.MovieDetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainLayerModule {

    @Provides
    fun provideGetMovieDetailUseCase(
        repository: MovieDetailRepository,
        mapper: MovieDetailMapper
    ): GetMovieDetailUseCase =
        GetMovieDetailUseCase(repository, mapper)

}