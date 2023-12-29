package com.esraemirli.filmvault.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation_constant.Screen
import com.core.navigation_api.NavigationApi

@Composable
fun AppNavGraph(navController: NavHostController, navigationProvider: NavigationProvider) {
    NavHost(
        navController = navController, startDestination = Screen.Home.route
    ) {
        register(
            api = navigationProvider.home,
            navController = navController
        )
        register(
            api = navigationProvider.movieDetail,
            navController = navController
        )

        register(
            api = navigationProvider.search,
            navController = navController
        )
    }
}

fun NavGraphBuilder.register(
    api: NavigationApi,
    navController: NavHostController
) {
    api.registerGraph(
        navGraphBuilder = this,
        navController = navController
    )
}