package com.feature.movie_detail.domain.mapper

import com.core.common.Mapper
import com.feature.movie_detail.domain.model.MovieDetailUiModel
import com.feature_movie_detail.data.model.MovieDetailResponse
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() : Mapper<MovieDetailResponse, MovieDetailUiModel> {
    override fun map(from: MovieDetailResponse): MovieDetailUiModel = with(from) {

        return MovieDetailUiModel(
            name = title,
            image = IMAGE_BASE_PATH + backdrop_path,
            runTime = runtime.getRunTime(),
            genres = genres.map { it.name }.take(3).joinToString(", "),
            overview = overview,
            homePageLink = homepage,
            vote = "%.1f".format(vote_average / 2.0).toDouble(),
            releaseYear = release_date.split("-").first(),
            isFavorite = false, //TODO take from backend
            isWatchList = false
        )
    }

    private fun Int.getRunTime(): String {
        val hour: Int = this / 60
        val minute = this - (hour * 60)

        return "${hour}h ${minute}min"
    }

    companion object {
        private const val IMAGE_BASE_PATH = "https://image.tmdb.org/t/p/original"
    }
}

