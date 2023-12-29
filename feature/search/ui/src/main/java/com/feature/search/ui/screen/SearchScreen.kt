package com.feature.search.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.common.domain.model.MovieUiModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToMovieDetail: (Int) -> Unit
) {
    val state = viewModel.movieList.collectAsState()

    when (state.value) {
        is SearchState.Loading -> Text(text = "LOADING")
        is SearchState.Content -> {
            val uiModel = (state.value as SearchState.Content).uiModel
            uiModel?.let {
                Content(
                    uiModel = uiModel
                )
            }
        }

        is SearchState.Error -> {
            val message = (state.value as SearchState.Error).message
            Text(text = message)
        }
    }
}

@Composable
fun Content(
    uiModel: List<MovieUiModel>,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 24.dp)
    ) {
            Text(text = uiModel[0].name, style = MaterialTheme.typography.h1, modifier = Modifier.size(300.dp))
    }
}

@Composable
fun SearchBar() {
    //TODO Text Field
    //TODO Filter
}