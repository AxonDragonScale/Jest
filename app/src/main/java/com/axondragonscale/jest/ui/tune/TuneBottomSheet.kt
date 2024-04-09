package com.axondragonscale.jest.ui.tune

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axondragonscale.jest.model.BlacklistFlag
import com.axondragonscale.jest.model.Category
import com.axondragonscale.jest.model.JokeType
import com.axondragonscale.jest.ui.theme.JestTheme

/**
 * Created by Ronak Harkhani on 07/04/24
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TuneBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface,
        dragHandle = { },
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
                .size(width = 48.dp, height = 4.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F),
                    shape = RoundedCornerShape(2.dp),
                ),
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = "Tell us your taste in Jokes",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary
            )

            MultiSelector(
                label = "CATEGORY",
                options = Category.entries.map { it.name.uppercase() }
            )

            // TODO: Add Language Selector here instead of this
//            Spacer(modifier = Modifier.height(64.dp))

            MultiSelector(
                modifier = Modifier.padding(top = 16.dp),
                label = "JOKE TYPE",
                options = JokeType.entries.map { it.name.uppercase() }
            )

            MultiSelector(
                modifier = Modifier.padding(top = 16.dp),
                label = "BLACKLIST",
                options = BlacklistFlag.entries.map { it.name.uppercase() }
            )
        }

        Spacer(modifier = Modifier.navigationBarsPadding())
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiSelector(
    modifier: Modifier = Modifier,
    label: String,
    options: List<String>
) = Column(modifier = modifier) {
    Text(
        modifier = Modifier.padding(bottom = 8.dp),
        text = label,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = FontWeight.Bold,
    )
    Row {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            options.forEach { option ->
                Text(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.surfaceTint,
                            shape = RoundedCornerShape(40)
                        )
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(40),
                        )
                        .defaultMinSize(minWidth = 64.dp, minHeight = 40.dp)
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .wrapContentSize(),
                    text = option,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TuneBottomSheetPreview() {
    JestTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            TuneBottomSheet(
                onDismiss = { }
            )
        }
    }
}
