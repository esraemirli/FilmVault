package com.feature_movie_detail.data

import com.feature_movie_detail.data.model.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailApiService {
    @GET("movie/{ID}")
    suspend fun getMovieDetail(
        @Path("ID") movieId: Int
    ): MovieDetailResponse
}
