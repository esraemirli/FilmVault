package com.feature.movie_detail.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.core.common.navigation_constant.NestedScreen
import com.core.navigation_api.NavigationApi
import com.feature.movie_detail.ui.screen.MovieDetailScreen

interface MovieDetailNavigation : NavigationApi

class MovieDetailNavigationImpl : MovieDetailNavigation {

    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.composable(route = NestedScreen.MovieDetail.route) {
            val movieId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>(
                NestedScreen.MovieDetail.parameterKey
            )
            MovieDetailScreen(navController, movieId)
        }
    }

}