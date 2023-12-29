package com.feature.search.domain.usecase

import com.core.common.UiEvent
import com.core.common.domain.mapper.MovieToMovieUiModelMapper
import com.feature.search.data.repository.MovieListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: MovieListRepository,
    private val movieToMovieUiModelMapper: MovieToMovieUiModelMapper
) {
    suspend fun invoke(keyword: String) = flow {
        emit(UiEvent.Loading())

        val response = repository.getMovies(keyword)

        val result = response.results.map {
            movieToMovieUiModelMapper.map(it)
        }

        emit(UiEvent.Success(result))
    }.catch { throwable ->
        emit(UiEvent.Error(throwable.message.toString()))
    }.flowOn(Dispatchers.IO)
}