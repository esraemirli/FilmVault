package com.feature.home.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.core.common.navigation_constant.Screen
import com.core.navigation_api.NavigationApi
import com.feature.home.ui.screen.HomeScreen
import com.feature.home.ui.screen.HomeViewModel

interface HomeNavigation : NavigationApi

class HomeNavigationImpl : HomeNavigation {
    override fun registerGraph(
        navController: NavHostController, navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.composable(route = Screen.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(viewModel, navController)
        }
    }

}