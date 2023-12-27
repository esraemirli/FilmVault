package com.feature.movie_detail.ui.di

import com.feature.movie_detail.ui.navigation.MovieDetailNavigation
import com.feature.movie_detail.ui.navigation.MovieDetailNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideMovieDetailPage(): MovieDetailNavigation = MovieDetailNavigationImpl()
}