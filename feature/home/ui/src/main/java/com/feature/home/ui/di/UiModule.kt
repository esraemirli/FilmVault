package com.feature.home.ui.di

import com.feature.home.ui.navigation.HomeNavigation
import com.feature.home.ui.navigation.HomeNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideHomePage(): HomeNavigation = HomeNavigationImpl()
}