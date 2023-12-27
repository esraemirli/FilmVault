package com.esraemirli.filmvault.di

import com.esraemirli.filmvault.navigation.NavigationProvider
import com.feature.home.ui.navigation.HomeNavigation
import com.feature.movie_detail.ui.navigation.MovieDetailNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideHomeNavigationProvider(home: HomeNavigation, movieDetail: MovieDetailNavigation): NavigationProvider =
        NavigationProvider(home, movieDetail)
}