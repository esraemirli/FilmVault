package com.esraemirli.filmvault.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation_constant.HomePage

@Composable
fun AppNavGraph(navController: NavHostController, navigationProvider: NavigationProvider) {
    NavHost(navController = navController, startDestination = HomePage.nestedRoute) {
        navigationProvider.home.registerGraph(
            navController, this
        )
        //TODO will call other's registerGraph
    }
}