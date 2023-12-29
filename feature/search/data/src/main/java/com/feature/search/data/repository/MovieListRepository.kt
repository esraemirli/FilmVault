package com.feature.search.data.repository

import com.feature.search.data.SearchApiService
import com.core.common.data.model.MovieListResponse
import javax.inject.Inject

interface MovieListRepository {
    suspend fun getMovies(keyword: String): MovieListResponse
}

class MovieListRepositoryImpl @Inject constructor(
    private val apiService: SearchApiService
) : MovieListRepository {
    override suspend fun getMovies(keyword: String): MovieListResponse {
        return apiService.getMovies(keyword)
    }
}