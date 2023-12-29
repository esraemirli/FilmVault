package com.feature.search.ui.di

import com.feature.search.ui.navigation.SearchNavigation
import com.feature.search.ui.navigation.SearchNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideHomePage(): SearchNavigation = SearchNavigationImpl()
}