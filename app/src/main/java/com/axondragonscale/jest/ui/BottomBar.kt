package com.axondragonscale.jest.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axondragonscale.jest.ui.theme.JestTheme

/**
 * Created by Ronak Harkhani on 06/04/24
 */

sealed class Tab(
    val name: String,
    val route: String,
    val activeIcon: ImageVector,
    val inactiveIcon: ImageVector,
) {

    data object Home : Tab(
        name = "HOME",
        route = "",
        activeIcon = Icons.Filled.Home,
        inactiveIcon = Icons.Outlined.Home,
    )

    data object Favorites : Tab(
        name = "FAVORITES",
        route = "",
        activeIcon = Icons.Filled.Favorite,
        inactiveIcon = Icons.Outlined.FavoriteBorder,
    )

}

@Composable
fun BottomTabBar(
    modifier: Modifier = Modifier,
    tabs: List<Tab>,
    activeTab: Tab,
    onTabChange: (Tab) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(40)
            )
            .padding(4.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        tabs.forEach { tab ->
            val isActive = tab == activeTab
            val interactionSource = remember { MutableInteractionSource() }
            val weight by animateFloatAsState(targetValue = if (isActive) 2f else 1f)
            val contentColor by animateColorAsState(
                if (isActive) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onPrimaryContainer
            )
            val backgroundColor by animateColorAsState(
                if (isActive) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.primaryContainer
            )
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onTabChange(tab)
                    }
                    .padding(4.dp)
                    .weight(weight)
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(40)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = if (isActive) tab.activeIcon else tab.inactiveIcon,
                    contentDescription = null,
                    tint = contentColor
                )


                AnimatedVisibility(isActive) {
                    Row {
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = tab.name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomBarPreview(modifier: Modifier = Modifier) {
    JestTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            BottomTabBar(
                modifier = Modifier.padding(horizontal = 56.dp, vertical = 16.dp),
                activeTab = Tab.Home,
                tabs = listOf(Tab.Home, Tab.Favorites),
                onTabChange = { }
            )
        }
    }
}