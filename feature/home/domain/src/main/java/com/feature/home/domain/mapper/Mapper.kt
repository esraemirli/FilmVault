package com.feature.home.domain.mapper

import com.core.common.Mapper
import com.feature.home.data.model.Movie
import com.feature.home.domain.model.HomeUiModel
import com.feature.home.domain.model.MovieUiModel
import com.feature.home.domain.model.SwimlaneUiModel
import com.feature.home.domain.usecase.MovieCategory
import javax.inject.Inject

class HomePageMapper @Inject constructor() : Mapper<HomePageParams, HomeUiModel> {

    override fun map(from: HomePageParams) = HomeUiModel(
        from.response.mapNotNull {
            SwimlaneUiModel(
                category = it.key.title,
                movies = it.value.mapNotNull { movie ->
                    if (movie.poster_path == null && it.key != MovieCategory.UP_COMING) {
                        null
                    } else if (movie.backdrop_path == null && it.key == MovieCategory.UP_COMING) {
                        null
                    } else {
                        MovieUiModel(
                            id = movie.id,
                            poster = IMAGE_BASE_PATH + movie.poster_path,
                            backdrop = IMAGE_BASE_PATH + movie.backdrop_path,
                            name = movie.title,
                            vote = movie.vote_average.toString()
                        )
                    }
                }
            )
        }
    )

    companion object {
        private const val IMAGE_BASE_PATH = "https://image.tmdb.org/t/p/original"
    }
}

data class HomePageParams(
    val response: Map<MovieCategory, List<Movie>>
)

