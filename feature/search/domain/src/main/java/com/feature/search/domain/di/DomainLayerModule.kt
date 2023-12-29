package com.feature.search.domain.di

import com.core.common.domain.mapper.MovieToMovieUiModelMapper
import com.feature.search.data.repository.MovieListRepository
import com.feature.search.domain.usecase.GetMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainLayerModule {

    @Provides
    fun provideGetMovieDetailUseCase(
        repository: MovieListRepository,
        mapper: MovieToMovieUiModelMapper
    ): GetMovieListUseCase =
        GetMovieListUseCase(repository, mapper)

}