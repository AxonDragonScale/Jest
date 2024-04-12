package com.axondragonscale.jest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axondragonscale.jest.model.OnePartJoke
import com.axondragonscale.jest.model.TwoPartJoke
import com.axondragonscale.jest.repository.AppPrefsRepository
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
) : ViewModel() {

    val uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val joke = repository.getCurrentJoke()
            uiState.update { HomeUiState.Success(joke) }
        }
    }

    fun onEvent(event: HomeUiEvent) = viewModelScope.launch(Dispatchers.IO) {
        when (event) {
            is HomeUiEvent.JokeAnimationComplete -> {
                uiState.update {
                    if (it is HomeUiState.Success) it.copy(shouldAnimateJoke = false)
                    else it
                }
            }


            is HomeUiEvent.NewJoke -> {
                uiState.update { HomeUiState.Loading }
                val joke = repository.getNewJoke()
                uiState.update { HomeUiState.Success(joke) }
            }

            is HomeUiEvent.FavoriteToggled -> {
                val updatedJoke = when (val joke = event.joke) {
                    is OnePartJoke -> joke.copy(favorite = event.favorite)
                    is TwoPartJoke -> joke.copy(favorite = event.favorite)
                }
                launch {
                    if (event.favorite) repository.addToFavorites(updatedJoke.id)
                    else repository.removeFromFavorites(updatedJoke.id)
                }
                uiState.update {
                    if (it is HomeUiState.Success) it.copy(joke = updatedJoke)
                    else it
                }
            }
        }
    }

}
