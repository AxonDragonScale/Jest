package com.axondragonscale.jest.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.axondragonscale.jest.ui.Route

/**
 * Created by Ronak Harkhani on 07/04/24
 */

fun NavGraphBuilder.homeNavGraph(navController: NavController) {
    composable(
        route = Route.Home.route,
        arguments = listOf(),
        deepLinks = emptyList()
    ) {
        Home(navController = navController)
    }
}
