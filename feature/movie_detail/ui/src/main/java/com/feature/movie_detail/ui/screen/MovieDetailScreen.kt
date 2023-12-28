package com.feature.movie_detail.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.feature.movie_detail.domain.model.MovieDetailUiModel
import com.feature.movie_detail.ui.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel, movieId: Int
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.getMovieDetail(movieId)
    }


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    MovieDetailEventHandler(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        eventsFlow = viewModel.eventsFlow
    )

    val state = viewModel.movieDetail.collectAsState()

    when (state.value) {
        is MovieDetailState.Loading -> androidx.compose.material.Text(text = "LOADING")
        is MovieDetailState.Content -> {
            val movieDetailUiModel = (state.value as MovieDetailState.Content).uiModel
            movieDetailUiModel?.let { uiModel ->

                Scaffold(
                    snackbarHost = {
                        Box(
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            SnackbarHost(hostState = snackbarHostState)
                        }
                    }
                ) {
                    Content(
                        uiModel = uiModel,
                        onClickedAction = { action, isActive ->
                            viewModel.clickToAction(action, isActive)
                        },
                        sharePageLink = { link ->
                            viewModel.sharePageLink(link)
                        },
                    )
                }
            }
        }

        is MovieDetailState.Error -> {
            val message = (state.value as MovieDetailState.Error).message
            Text(text = message)
        }
    }

}

@Composable
fun Content(
    uiModel: MovieDetailUiModel,
    onClickedAction: (Action, Boolean) -> Unit,
    sharePageLink: (String) -> Unit,
) {

    val scrollState = rememberScrollState()

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 24.dp)
    ) {
        val imageHeight = maxHeight.value / 1.5

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight.dp)
                    .clip(shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
            ) {
                AsyncImage(
                    placeholder = painterResource(id = R.drawable.baseline_movie),
                    model = uiModel.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 42.dp),
                    text = uiModel.name,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(MaterialTheme.colorScheme.onSurface)
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = uiModel.genres,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = uiModel.releaseYear,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = uiModel.runTime, style = MaterialTheme.typography.bodyMedium
                    )
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionItem(
                    action = Action.ADD_WATCHLIST,
                    isActive = uiModel.isWatchList,
                    onClickedAction = onClickedAction
                )
                ActionItem(
                    action = Action.ADD_FAVORITE,
                    isActive = uiModel.isFavorite,
                    onClickedAction = onClickedAction
                )
                ActionItem(action = Action.SHARE,
                    isClickable = uiModel.homePageLink.isNotEmpty(),
                    onClickedAction = { _, _ ->
                        sharePageLink(uiModel.homePageLink)
                    })
            }

            RatingBar(rating = uiModel.vote)

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = uiModel.overview,
            )
        }
    }
}


@Composable
fun ActionItem(
    action: Action,
    isClickable: Boolean = true,
    isActive: Boolean = false,
    onClickedAction: (Action, Boolean) -> Unit
) {

    var isEnabled by remember { mutableStateOf(isActive) }

    Column(
        modifier = Modifier.clickable(enabled = isClickable) {
            onClickedAction(action, isEnabled)
            if (action != Action.SHARE) isEnabled = !isEnabled
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            imageVector = if (isEnabled) action.icon2 else action.icon,
            contentDescription = null,
            modifier = Modifier.padding(bottom = 8.dp),
            tint = if (isEnabled) Color.Red else if (isClickable) Color.White else Color.Gray
        )

        Text(
            text = action.text, color = if (isClickable) Color.White else Color.Gray
        )
    }
}

@Composable
fun RatingBar(
    rating: Double
) {

    val outlinedStar = painterResource(id = R.drawable.outlined_star)
    val halfStar = painterResource(id = R.drawable.half_star)
    val filledStar = painterResource(id = R.drawable.filled_star)


    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        repeat(5) { index ->
            Image(
                painter = if (index + 1 < rating) {
                    filledStar
                } else {
                    if ((rating - index) > 0.0) {
                        halfStar
                    } else outlinedStar
                },
                contentDescription = null,
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp),
            text = rating.toString(),
        )
    }
}

enum class Action(val icon: ImageVector, val icon2: ImageVector, val text: String) {
    ADD_WATCHLIST(
        icon = Icons.Default.Add, icon2 = Icons.Default.Check, text = "Watchlist"
    ),
    ADD_FAVORITE(
        icon = Icons.Default.FavoriteBorder, icon2 = Icons.Default.Favorite, text = "Favorite"
    ),
    SHARE(icon = Icons.Default.Share, icon2 = Icons.Default.Share, text = "Share"),
}