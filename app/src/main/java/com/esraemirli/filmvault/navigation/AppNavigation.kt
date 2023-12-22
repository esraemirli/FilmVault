package com.esraemirli.filmvault.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation_constant.Screen

@Composable
fun AppNavGraph(navController: NavHostController, navigationProvider: NavigationProvider) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        navigationProvider.home.registerGraph(
            navController, this
        )
        //TODO will call other's registerGraph
    }
}