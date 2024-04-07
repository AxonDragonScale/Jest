package com.axondragonscale.jest.ui

/**
 * Created by Ronak Harkhani on 07/04/24
 */
sealed class Route(val route: String) {
    data object Home: Route("home")
    data object Favorites: Route("favorites")
}
