package com.axondragonscale.jest.ui.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.axondragonscale.jest.ui.Route

/**
 * Created by Ronak Harkhani on 06/04/24
 */

sealed class Tab(
    val name: String,
    val route: Route,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector,
) {

    data object Home : Tab(
        name = "HOME",
        route = Route.Home,
        activeIcon = Icons.Filled.Home,
        inactiveIcon = Icons.Outlined.Home,
    )

    data object Favorites : Tab(
        name = "FAVORITES",
        route = Route.Favorites,
        activeIcon = Icons.Filled.Favorite,
        inactiveIcon = Icons.Outlined.FavoriteBorder,
    )

}
