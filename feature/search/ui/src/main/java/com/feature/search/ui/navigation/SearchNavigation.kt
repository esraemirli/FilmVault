package com.feature.search.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.core.common.navigation_constant.NestedScreen
import com.core.common.navigation_constant.Screen
import com.core.navigation_api.NavigationApi
import com.feature.search.ui.screen.SearchScreen
import com.feature.search.ui.screen.SearchViewModel

interface SearchNavigation : NavigationApi

class SearchNavigationImpl : SearchNavigation {

    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(route = Screen.Search.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                viewModel = viewModel,
                navigateToMovieDetail = { movieId ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        NestedScreen.MovieDetail.parameterKey, movieId
                    )

                    navController.navigate(NestedScreen.MovieDetail.route)
                }
            )
        }
    }
}