package com.feature.home.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constant.HomePage
import com.core.navigation_api.NavigationApi
import com.feature.home.ui.screen.HomeScreen

interface HomeNavigation: NavigationApi

class HomeNavigationImpl : HomeNavigation {
    override fun registerGraph(
        navController: NavHostController, navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = HomePage.screenRoute,
            route = HomePage.nestedRoute
        ) {
            composable(HomePage.screenRoute) {
                HomeScreen()
            }
        }
    }

}