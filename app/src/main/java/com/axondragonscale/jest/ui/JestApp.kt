package com.axondragonscale.jest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.axondragonscale.jest.ui.bottombar.BottomTabBar
import com.axondragonscale.jest.ui.bottombar.Tab
import com.axondragonscale.jest.ui.favorites.favoritesNavGraph
import com.axondragonscale.jest.ui.home.homeNavGraph

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
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.Home.route
        ) {
            homeNavGraph(navController)
            favoritesNavGraph(navController)
        }

        var activeTab by remember { mutableStateOf<Tab>(Tab.Home) }
        BottomTabBar(
            modifier = Modifier
                .padding(horizontal = 56.dp, vertical = 16.dp)
                .align(Alignment.BottomCenter),
            tabs = listOf(Tab.Home, Tab.Favorites),
            activeTab = activeTab,
            onTabChange = {
                navController.navigate(it.route.route)
                activeTab = it
            }
        )
    }
}
