package com.feature.movie_detail.ui.screen

import com.feature.movie_detail.domain.model.MovieDetailUiModel

sealed class MovieDetailState {
    data object Loading : MovieDetailState()

    data class Content(
        val uiModel: MovieDetailUiModel?
    ) : MovieDetailState()

    data class Error(
        val message: String
    ) : MovieDetailState()
}