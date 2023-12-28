package com.feature_movie_detail.data.repository

import com.feature_movie_detail.data.MovieDetailApiService
import com.feature_movie_detail.data.model.MovieDetailResponse
import javax.inject.Inject

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse
}

class MovieDetailRepositoryImpl @Inject constructor(
    private val apiService: MovieDetailApiService
) : MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: Int): MovieDetailResponse {
        return apiService.getMovieDetail(movieId)
    }
}