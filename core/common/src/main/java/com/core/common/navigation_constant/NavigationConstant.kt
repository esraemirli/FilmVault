package com.core.common.navigation_constant

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(val route: String, val icon: ImageVector, val label: String) {
    Home("home", Icons.Default.Home, "Home"),
    Search("search", Icons.Default.Search, "Search"),
    Favorite("favorite", Icons.Default.Favorite, "My Favorite"),
    Profile("profile", Icons.Default.AccountBox, "Profile")
}

enum class NestedScreen(val route: String, val parameterKey: String) {
    MovieDetail("home/movieDetail", "movieId")
}

//TODO Create other page's feature