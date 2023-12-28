package com.feature.movie_detail.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.feature.movie_detail.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _movieDetail = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val movieDetail: StateFlow<MovieDetailState> get() = _movieDetail.asStateFlow()

    private val eventChannel = Channel<MovieDetailEvent>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        getMovieDetailUseCase.invoke(movieId).onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _movieDetail.value = MovieDetailState.Loading
                }

                is UiEvent.Error -> {
                    _movieDetail.value = MovieDetailState.Error(it.message.toString())
                }

                is UiEvent.Success -> {
                    _movieDetail.value = MovieDetailState.Content(uiModel = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun clickToAction(action: Action, isActive: Boolean) {
        when (action) {
            Action.ADD_FAVORITE -> {
                if (isActive) sendEvent(MovieDetailEvent.RemoveFromFavorite)
                else sendEvent(MovieDetailEvent.AddToFavorite)

            }

            Action.ADD_WATCHLIST -> {
                if (isActive) sendEvent(MovieDetailEvent.RemoveFromWatchList)
                else sendEvent(MovieDetailEvent.AddToWatchList)
            }

            else -> {}
        }
    }

    fun sharePageLink(link: String) {
        sendEvent(MovieDetailEvent.ShareHomePageLink(link))
    }

    private fun sendEvent(event: MovieDetailEvent) {
        viewModelScope.launch {
            eventChannel.send(event)
        }
    }


}