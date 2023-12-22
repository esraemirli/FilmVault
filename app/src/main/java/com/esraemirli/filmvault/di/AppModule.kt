package com.esraemirli.filmvault.di

import com.esraemirli.filmvault.navigation.NavigationProvider
import com.feature.home.ui.navigation.HomeNavigation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideHomeNavigationProvider(home: HomeNavigation): NavigationProvider =
        NavigationProvider(home)
}