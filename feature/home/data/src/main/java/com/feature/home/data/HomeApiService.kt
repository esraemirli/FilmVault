package com.feature.home.data

import com.feature.home.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApiService {
    @GET("movie/{Category}")
    suspend fun getMovieList(
        @Path("Category") category: String
    ): MovieListResponse
}
