package com.core.navigation_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavigationApi {

    fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    )
}