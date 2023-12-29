package com.feature.search.ui.screen

import com.core.common.domain.model.MovieUiModel

sealed class SearchState {
    data object Loading : SearchState()

    data class Content(
        val uiModel: List<MovieUiModel>?
    ) : SearchState()

    data class Error(
        val message: String
    ) : SearchState()
}