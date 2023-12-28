package com.feature.movie_detail.domain.usecase

import com.core.common.UiEvent
import com.feature.movie_detail.domain.mapper.MovieDetailMapper
import com.feature_movie_detail.data.repository.MovieDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val mapper: MovieDetailMapper
) {
    suspend fun invoke(movieId: Int) = flow {
        emit(UiEvent.Loading())


        val response = repository.getMovieDetail(movieId)

        val result = mapper.map(from = response)

        emit(UiEvent.Success(result))
    }.catch { throwable ->
        emit(UiEvent.Error(throwable.message.toString()))
    }.flowOn(Dispatchers.IO)
}