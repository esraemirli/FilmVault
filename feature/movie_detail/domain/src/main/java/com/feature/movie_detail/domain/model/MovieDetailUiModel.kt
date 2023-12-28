package com.feature.movie_detail.domain.model

data class MovieDetailUiModel(
    val name: String,
    val image: String,
    val runTime: String,
    val genres: String,
    val releaseYear: String,
    val overview: String,
    val homePageLink: String,
    val vote: Double,
    val isFavorite: Boolean,
    val isWatchList: Boolean
)