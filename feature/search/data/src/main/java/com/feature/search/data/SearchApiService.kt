package com.feature.search.data

import com.core.common.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("search/movie?")
    suspend fun getMovies(
        @Query("query") keyword: String
    ): MovieListResponse
}
