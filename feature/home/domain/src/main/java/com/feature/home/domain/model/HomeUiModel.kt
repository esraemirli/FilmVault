package com.feature.home.domain.model

data class SwimlaneUiModel(
    val category: String,
    val movies: List<MovieUiModel>
)

data class HomeUiModel(
    val swimlanes: List<SwimlaneUiModel>
)