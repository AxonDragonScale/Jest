package com.axondragonscale.jest.ui.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Ronak Harkhani on 06/04/24
 */

sealed class Tab(
    val name: String,
    val position: Int,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector,
) {

    companion object {
        val all = listOf(Home, Favorites)
    }

    data object Home : Tab(
        name = "HOME",
        position = 0,
        activeIcon = Icons.Filled.Home,
        inactiveIcon = Icons.Outlined.Home,
    )

    data object Favorites : Tab(
        name = "FAVORITES",
        position = 1,
        activeIcon = Icons.Filled.Favorite,
        inactiveIcon = Icons.Outlined.FavoriteBorder,
    )

}
