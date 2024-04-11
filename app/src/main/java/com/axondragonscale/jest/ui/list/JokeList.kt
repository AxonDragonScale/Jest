package com.axondragonscale.jest.ui.list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.axondragonscale.jest.model.Category
import com.axondragonscale.jest.model.Flags
import com.axondragonscale.jest.model.JokeType
import com.axondragonscale.jest.model.Language
import com.axondragonscale.jest.model.OnePartJoke
import com.axondragonscale.jest.model.TwoPartJoke
import com.axondragonscale.jest.ui.common.JokeCard
import com.axondragonscale.jest.ui.theme.JestTheme

/**
 * Created by Ronak Harkhani on 07/04/24
 */

@Composable
fun JokeList(
    vm: JokeListVM = hiltViewModel(),
    listType: JokeListType,
) {
    LaunchedEffect(listType) {
        vm.init(listType)
    }

    val uiState by vm.uiState.collectAsStateWithLifecycle()
    JokeList(uiState = uiState)
}

@Composable
fun JokeList(
    modifier: Modifier = Modifier,
    uiState: JokeListUiState,
) {
    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(uiState.jokes, key = { it.id }) { joke ->
            JokeCard(
                joke = joke,
                shouldAnimateJoke = false,
                onAnimationComplete = { },
                onFavoriteToggled = { },
                onShareClick = { }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun JokeListPreview() {
    JestTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            JokeList(
                uiState = JokeListUiState(
                    listOf(
                        OnePartJoke(
                            id = 1,
                            lang = Language.English,
                            category = Category.Pun,
                            flags = Flags(false, false, false, false, false, false),
                            safe = true,
                            type = JokeType.Single,
                            joke = "Lorem Ipsum"
                        ),
                        TwoPartJoke(
                            id = 2,
                            lang = Language.English,
                            category = Category.Pun,
                            flags = Flags(false, false, false, false, false, false),
                            safe = true,
                            type = JokeType.TwoPart,
                            setup = "Lorem Ipsum",
                            delivery = "Lorem Ipsum"
                        )
                    )
                )
            )
        }
    }
}
