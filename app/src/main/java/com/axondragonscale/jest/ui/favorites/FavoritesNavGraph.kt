package com.axondragonscale.jest.ui.favorites

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.axondragonscale.jest.ui.Route

/**
 * Created by Ronak Harkhani on 07/04/24
 */

fun NavGraphBuilder.favoritesNavGraph(navController: NavController) {
    composable(
        route = Route.Favorites.route,
        arguments = listOf(),
        deepLinks = listOf()
    ) {
        Favorites(navController = navController)
    }
}
