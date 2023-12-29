package com.core.common.domain.mapper

import com.core.common.Mapper
import com.core.common.data.model.Movie
import com.core.common.domain.model.MovieUiModel
import javax.inject.Inject

class MovieToMovieUiModelMapper @Inject constructor() : Mapper<Movie, MovieUiModel> {
    override fun map(from: Movie): MovieUiModel = with(from) {
        return MovieUiModel(
            id = id,
            poster = IMAGE_BASE_PATH + poster_path,
            backdrop = IMAGE_BASE_PATH + backdrop_path,
            name = title,
            vote = vote_average.toString()
        )
    }

    companion object {
        private const val IMAGE_BASE_PATH = "https://image.tmdb.org/t/p/original"
    }

}