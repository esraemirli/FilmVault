package com.feature.home.ui.screen

import com.feature.home.domain.model.HomeUiModel

sealed class HomeState {
    data object Loading : HomeState()

    data class Content(
        val uiModel: HomeUiModel?
    ) : HomeState()

    data class Error(
        val message: String
    ) : HomeState()
}