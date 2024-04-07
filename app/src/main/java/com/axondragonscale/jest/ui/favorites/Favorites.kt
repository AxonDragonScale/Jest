package com.axondragonscale.jest.ui.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

/**
 * Created by Ronak Harkhani on 07/04/24
 */

@Composable
fun Favorites(navController: NavController, vm: FavoritesVM = hiltViewModel()) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    Favorites(uiState = uiState)
}

@Composable
fun Favorites(
    modifier: Modifier = Modifier,
    uiState: FavoritesUiState,
) {

}
