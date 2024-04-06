package com.axondragonscale.jest.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.axondragonscale.jest.model.Category
import com.axondragonscale.jest.model.Flags
import com.axondragonscale.jest.model.IJoke
import com.axondragonscale.jest.model.JokeType
import com.axondragonscale.jest.model.Language
import com.axondragonscale.jest.model.OnePartJoke
import com.axondragonscale.jest.model.getFirstLine
import com.axondragonscale.jest.model.getSecondLine
import com.axondragonscale.jest.ui.component.TypewriterText
import kotlinx.coroutines.delay

/**
 * Created by Ronak Harkhani on 01/04/24
 */

@Composable
fun Home(vm: HomeVM = hiltViewModel()) {
    val uiState by vm.uiState.collectAsStateWithLifecycle()
    Home(
        uiState = uiState,
        onEvent = { vm.onEvent(it) }
    )
}

@Composable
fun Home(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HomeHeader()

        Spacer(modifier = Modifier.weight(1f))

        JokeCard(Modifier, uiState)

        Spacer(modifier = Modifier.height(16.dp))

        JokeControls(
            onTuneClick = {},
            onRandomClick = { onEvent(HomeUiEvent.NewJoke) }
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun JokeControls(
    modifier: Modifier = Modifier,
    onTuneClick: () -> Unit,
    onRandomClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        JestIconButton(icon = Icons.Default.Tune, onClick = onTuneClick)
        Spacer(modifier = Modifier.width(16.dp))
        JestIconButton(icon = Icons.Default.Shuffle, onClick = onRandomClick)
    }
}

@Composable
fun JestIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
    size: Dp = 56.dp,
) {
    // Use Button, IconButton ripple doesn't scale with size
    Button(
        modifier = modifier.size(size),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
private fun HomeHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Jest",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "Your daily dose of humour",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun JokeCard(
    modifier: Modifier,
    uiState: HomeUiState
) = Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
    shape = RoundedCornerShape(16.dp),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        OpeningQuote()

        if (uiState is HomeUiState.Success) {
            JokeText(uiState.joke)
        }

        ClosingQuote(modifier = Modifier.align(Alignment.End))

        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FavoriteButton()
            ShareButton()
            Spacer(modifier = Modifier.weight(1f))
            if (uiState is HomeUiState.Success) {
                JokeTag(tag = uiState.joke.category.name)
            }
        }
    }
}


@Composable
private fun JokeText(joke: IJoke) {
    var showSecondLine by remember(joke) { mutableStateOf(false) }
    TypewriterText(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 28.dp),
        text = joke.getFirstLine(),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        onEffectComplete = {
            delay(1000)
            showSecondLine = true
        }
    )

    val secondLine by remember(joke) { mutableStateOf(joke.getSecondLine()) }
    secondLine?.let {
        TypewriterText(
            modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 28.dp),
            text = it,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            showText = showSecondLine
        )
    }
}

@Composable
private fun JokeTag(tag: String) {
    Text(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.onPrimary,
                shape = RoundedCornerShape(8.dp)
            )
            .defaultMinSize(minHeight = 24.dp)
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .wrapContentHeight(align = Alignment.CenterVertically),
        text = tag.uppercase(),
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
    )
}

@Composable
private fun FavoriteButton(modifier: Modifier = Modifier) {
    val checked = false
    IconToggleButton(
        modifier = modifier,
        checked = false,
        onCheckedChange = {}
    ) {
        Icon(
            imageVector =
            if (checked) Icons.Default.Favorite
            else Icons.Default.FavoriteBorder,
            contentDescription = null
        )
    }
}

@Composable
private fun ShareButton(modifier: Modifier = Modifier) {
    IconButton(
        modifier = modifier,
        onClick = { /*TODO*/ }
    ) {
        Icon(
            imageVector = Icons.Default.IosShare,
            contentDescription = null
        )
    }
}

@Composable
private fun OpeningQuote(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier
            .offset(x = -4.dp)
            .padding(bottom = 8.dp)
            .size(32.dp)
            .graphicsLayer(rotationY = 180f),
        imageVector = Icons.Default.FormatQuote,
        contentDescription = null
    )
}

@Composable
private fun ClosingQuote(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier
            .padding(top = 4.dp)
            .size(20.dp)
            .offset(x = 4.dp),
        imageVector = Icons.Default.FormatQuote,
        contentDescription = null
    )
}

@Preview
@Composable
fun HomePreview() {
    MaterialTheme {
        Home(
            modifier = Modifier,
            uiState = HomeUiState.Success(
                joke = OnePartJoke(
                    id = 1,
                    lang = Language.English,
                    category = Category.Pun,
                    flags = Flags(false, false, false, false, false, false),
                    safe = true,
                    type = JokeType.Single,
                    joke = "Lorem Ipsum"
                )
            ),
            onEvent = {}
        )
    }
}
