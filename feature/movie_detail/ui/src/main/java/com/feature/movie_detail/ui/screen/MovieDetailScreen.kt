package com.feature.movie_detail.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MovieDetailScreen(navController: NavHostController, movieId: Int?) {

    Text("ESRA DETAIL --  $movieId")
}