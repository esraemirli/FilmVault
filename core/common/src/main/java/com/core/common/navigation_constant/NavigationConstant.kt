package com.core.common.navigation_constant

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    data object Home : Screen("home_screen", Icons.Default.Home, "Home")
    data object Search : Screen("search_screen", Icons.Default.Search, "Search")
    data object Favorite : Screen("favorite_screen", Icons.Default.Favorite, "My Favorite")
    data object Profile : Screen("profile_screen", Icons.Default.AccountBox, "Profile")
}

//TODO Create other page's feature