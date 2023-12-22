package com.feature.home.data.repository

import com.feature.home.data.HomeApiService
import com.feature.home.data.model.Movie
import javax.inject.Inject

interface HomePageRepository {
    suspend fun getMovieList(category: String): List<Movie>
}

class HomePageRepositoryImpl @Inject constructor(
    private val apiService: HomeApiService
) : HomePageRepository {
    override suspend fun getMovieList(category: String): List<Movie> {
        return apiService.getMovieList(category).results
    }
}