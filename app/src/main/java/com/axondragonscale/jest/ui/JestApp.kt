package com.axondragonscale.jest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.axondragonscale.jest.ui.home.Home

/**
 * Created by Ronak Harkhani on 01/04/24
 */

@Composable
fun JestApp() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Home()
    }
}
