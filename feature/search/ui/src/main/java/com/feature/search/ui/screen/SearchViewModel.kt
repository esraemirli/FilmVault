package com.feature.search.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.UiEvent
import com.feature.search.domain.usecase.GetMovieListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private val _movieList = MutableStateFlow<SearchState>(SearchState.Loading)
    val movieList: StateFlow<SearchState> get() = _movieList.asStateFlow()


    init {
        getMovieList("Spider")
    }

    private fun getMovieList(keyword: String) = viewModelScope.launch {
        getMovieListUseCase.invoke(keyword).onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _movieList.value = SearchState.Loading
                }

                is UiEvent.Error -> {
                    _movieList.value = SearchState.Error(it.message.toString())
                }

                is UiEvent.Success -> {
                    _movieList.value = SearchState.Content(uiModel = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}