package com.axondragonscale.jest.ui.favorites

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.axondragonscale.jest.ui.theme.JestTheme

/**
 * Created by Ronak Harkhani on 07/04/24
 */

@Composable
fun Favorites(vm: FavoritesVM = hiltViewModel()) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    Favorites(uiState = uiState)
}

@Composable
fun Favorites(
    modifier: Modifier = Modifier,
    uiState: FavoritesUiState,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Favorites",
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun FavoritesPreview() {
    JestTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            Favorites(
                uiState = FavoritesUiState(listOf())
            )
        }
    }
}
