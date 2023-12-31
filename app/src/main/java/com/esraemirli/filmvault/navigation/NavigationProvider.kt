package com.esraemirli.filmvault.navigation

import com.feature.home.ui.navigation.HomeNavigation
import com.feature.movie_detail.ui.navigation.MovieDetailNavigation
import com.feature.search.ui.navigation.SearchNavigation


//Collect each page's navigation api
data class NavigationProvider(
    val home: HomeNavigation,
    val movieDetail: MovieDetailNavigation,
    val search: SearchNavigation
)
