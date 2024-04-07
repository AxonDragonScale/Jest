package com.axondragonscale.jest.ui.favorites

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Created by Ronak Harkhani on 07/04/24
 */
@HiltViewModel
class FavoritesVM @Inject constructor() : ViewModel() {

    val uiState = MutableStateFlow(FavoritesUiState(listOf()))

}
