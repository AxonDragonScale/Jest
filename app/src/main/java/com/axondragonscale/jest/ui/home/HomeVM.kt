package com.axondragonscale.jest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axondragonscale.jest.repository.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 01/04/24
 */
@HiltViewModel
class HomeVM @Inject constructor(
    private val repository: JokeRepository,
): ViewModel() {

    val uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getNewJoke()
        }
    }

    fun onEvent(event: HomeUiEvent) = viewModelScope.launch {
        when (event) {
            is HomeUiEvent.NewJoke -> getNewJoke()
        }
    }

    private suspend fun getNewJoke() {
        val joke = repository.getRandomJoke()
        uiState.update {
            HomeUiState.Success(joke)
        }
    }

}
