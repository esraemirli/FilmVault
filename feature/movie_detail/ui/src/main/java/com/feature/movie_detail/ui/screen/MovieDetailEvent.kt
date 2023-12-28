package com.feature.movie_detail.ui.screen

import android.content.Intent
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

sealed class MovieDetailEvent {
    data object AddToFavorite : MovieDetailEvent()
    data object RemoveFromFavorite : MovieDetailEvent()
    data object AddToWatchList : MovieDetailEvent()
    data object RemoveFromWatchList : MovieDetailEvent()
    data class ShareHomePageLink(val link: String) : MovieDetailEvent()
}

@Composable
fun MovieDetailEventHandler(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    eventsFlow: Flow<MovieDetailEvent>
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {

        eventsFlow.collectLatest { event ->
            when (event) {

                is MovieDetailEvent.AddToFavorite -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Movie added to favorite"
                        )
                    }
                }

                is MovieDetailEvent.RemoveFromFavorite -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Movie removed from favorite"
                        )
                    }
                }

                is MovieDetailEvent.AddToWatchList -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Movie added to watchlist"
                        )
                    }
                }

                is MovieDetailEvent.RemoveFromWatchList -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Movie removed from watchlist"
                        )
                    }
                }

                is MovieDetailEvent.ShareHomePageLink -> {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, event.link)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(sendIntent, null)

                    startActivity(context, shareIntent, null)
                }
            }
        }
    }
}