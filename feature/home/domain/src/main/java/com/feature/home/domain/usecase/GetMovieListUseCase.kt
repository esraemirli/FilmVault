package com.feature.home.domain.usecase

import com.core.common.UiEvent
import com.feature.home.data.repository.HomePageRepository
import com.feature.home.domain.mapper.HomePageMapper
import com.feature.home.domain.mapper.HomePageParams
import com.feature.home.domain.usecase.MovieCategory.NOW_PLAYING
import com.feature.home.domain.usecase.MovieCategory.POPULAR
import com.feature.home.domain.usecase.MovieCategory.TOP_RATED
import com.feature.home.domain.usecase.MovieCategory.UP_COMING
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: HomePageRepository, private val mapper: HomePageMapper
) {
    suspend fun invoke() = flow {
        emit(UiEvent.Loading())

        val categoryList = listOf(UP_COMING, NOW_PLAYING, TOP_RATED, POPULAR)

        val response = categoryList.associateWith { category ->
            repository.getMovieList(category.requestPath)
        }
        val result = mapper.map(from = HomePageParams(response))

        emit(UiEvent.Success(result))
    }.catch { throwable ->
        emit(UiEvent.Error(throwable.message.toString()))
    }.flowOn(Dispatchers.IO)
}

enum class MovieCategory(val requestPath: String, val title: String) {
    UP_COMING(requestPath = "upcoming", title = "Up Coming"),
    NOW_PLAYING(requestPath = "now_playing", title = "Now Playing"),
    TOP_RATED(requestPath = "top_rated", title = "Top Rated"),
    POPULAR(requestPath = "popular", title = "Popular")
}