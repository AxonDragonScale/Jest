package com.axondragonscale.jest.ui.list

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
 * Created by Ronak Harkhani on 07/04/24
 */
@HiltViewModel
class JokeListVM @Inject constructor(
    private val repository: JokeRepository,
) : ViewModel() {

    private lateinit var listType: JokeListType

    val uiState = MutableStateFlow(JokeListUiState(listOf()))

    fun init(listType: JokeListType) {
        this.listType = listType
        viewModelScope.launch(Dispatchers.IO) {
            val jokesFlow = when (listType) {
                JokeListType.History -> repository.getAllJokes()
                JokeListType.Favorites -> repository.getFavoriteJokes()
            }
            jokesFlow.collect { jokes ->
                uiState.update {
                    JokeListUiState(jokes)
                }
            }
        }
    }

    fun onEvent() {

    }

}
