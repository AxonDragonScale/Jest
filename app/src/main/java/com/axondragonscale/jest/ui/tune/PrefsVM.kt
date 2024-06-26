package com.axondragonscale.jest.ui.tune

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axondragonscale.jest.model.BlacklistFlag
import com.axondragonscale.jest.model.Category
import com.axondragonscale.jest.model.JokeType
import com.axondragonscale.jest.repository.PrefsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 10/04/24
 */
@HiltViewModel
class PrefsVM @Inject constructor(
    private val prefsRepository: PrefsRepository,
) : ViewModel() {

    val uiState: StateFlow<PrefsUiState> =
        prefsUiStateFlow()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = PrefsUiState()
            )

    fun onEvent(event: PrefsUiEvent) = viewModelScope.launch(Dispatchers.IO) {
        when (event) {
            is PrefsUiEvent.CategorySelectionChanged -> {
                prefsRepository.setJokeCategories(
                    event.selectedCategories.map { Category.fromOrdinal(it) }
                )
            }

            is PrefsUiEvent.JokeTypeSelectionChanged -> {
                prefsRepository.setJokeTypes(
                    event.selectedJokeTypes.map { JokeType.fromOrdinal(it) }
                )
            }

            is PrefsUiEvent.BlacklistFlagSelectionChanged -> {
                prefsRepository.setBlacklistFlags(
                    event.selectedBlacklistFlags.map { BlacklistFlag.fromOrdinal(it) }
                )
            }
        }
    }

    private fun prefsUiStateFlow(): Flow<PrefsUiState> =
        combine(
            prefsRepository.jokeCategoriesFlow,
            prefsRepository.jokeTypesFlow,
            prefsRepository.blacklistFlagsFlow
        ) { jokeCategories, jokeTypes, blacklistFlags ->
            PrefsUiState(
                selectedCategories = jokeCategories,
                selectedJokeTypes = jokeTypes,
                selectedBlacklistFlags = blacklistFlags,
            )
        }

}
