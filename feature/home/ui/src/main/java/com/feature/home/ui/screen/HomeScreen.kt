package com.feature.home.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.feature.home.domain.model.HomeUiModel
import com.feature.home.domain.model.MovieUiModel
import com.feature.home.domain.model.SwimlaneUiModel
import com.feature.home.domain.usecase.MovieCategory
import com.feature.home.ui.R

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navHostController: NavHostController
) {
    val state = viewModel.movieList.collectAsState()

    when (state.value) {
        is HomeState.Loading -> Text(text = "LOADING")
        is HomeState.Content -> {
            val uiModel = (state.value as HomeState.Content).uiModel
            uiModel?.let { Content(uiModel) }
        }

        is HomeState.Error -> {
            val message = (state.value as HomeState.Error).message
            Text(text = message)
        }
    }
}

@Composable
fun Content(
    uiModel: HomeUiModel
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 24.dp)
    ) {

        uiModel.swimlanes.forEachIndexed { index, swimlaneUiModel ->

            if (index == MovieCategory.UP_COMING.ordinal) {
                UpComingMovies(swimlaneUiModel.movies)

            } else {
                SwimlaneMovies(swimlaneUiModel)
            }
        }
    }
}

@Composable
fun SwimlaneMovies(
    swimlaneUiModel: SwimlaneUiModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = swimlaneUiModel.category,
            style = MaterialTheme.typography.titleSmall
        )

        Image(
            modifier = Modifier.size(18.dp),
            painter = painterResource(id = R.drawable.baseline_arrow),
            contentDescription = null,
        )
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(swimlaneUiModel.movies) { movie ->
            AsyncImage(
                modifier = Modifier
                    .size(width = 150.dp, height = 200.dp)
                    .padding(end = 8.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                placeholder = painterResource(id = R.drawable.baseline_movie),
                model = movie.poster,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UpComingMovies(
    movieUiModels: List<MovieUiModel>
) {
    val pagerState = rememberPagerState { movieUiModels.size }

    HorizontalPager(state = pagerState) { index ->
        val movie = movieUiModels[index]

        Box(modifier = Modifier.wrapContentSize()) {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)),
                placeholder = painterResource(id = R.drawable.baseline_movie),
                model = movie.backdrop,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 36.dp, start = 16.dp),
                text = movie.name,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 8.dp, start = 16.dp),
                text = movie.vote,
                style = MaterialTheme.typography.labelSmall
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 8.dp, end = 16.dp),
                text = "${index + 1} - ${movieUiModels.size}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
