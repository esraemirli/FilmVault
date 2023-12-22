package com.feature.home.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.feature.home.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private val _movieList = MutableStateFlow<HomeState>(HomeState.Loading)
    val movieList: StateFlow<HomeState> get() = _movieList.asStateFlow()

    init {
        getMovieList()
    }

    private fun getMovieList() = viewModelScope.launch {
        getMovieListUseCase.invoke().onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _movieList.value = HomeState.Loading
                }

                is UiEvent.Error -> {
                    _movieList.value = HomeState.Error(it.message.toString())
                }

                is UiEvent.Success -> {
                    _movieList.value = HomeState.Content(uiModel = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}