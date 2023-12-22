package com.esraemirli.filmvault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.core.common.navigation_constant.Screen
import com.esraemirli.filmvault.navigation.AppNavGraph
import com.esraemirli.filmvault.navigation.NavigationProvider
import com.esraemirli.filmvault.ui.theme.FilmVaultTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilmVaultTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        NavigationBottomBar(navController, navigationProvider)
                    }
                ) { bottom ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = bottom.calculateBottomPadding()),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        AppNavGraph(
                            navController = navController, navigationProvider = navigationProvider
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationBottomBar(navController: NavHostController, navigationProvider: NavigationProvider) {

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val bottomItems = listOf(Screen.Home, Screen.Search, Screen.Favorite, Screen.Profile)

        bottomItems.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.White,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { Icon(item.icon, null) },
                label = { Text(item.label) }
            )
        }
    }
}