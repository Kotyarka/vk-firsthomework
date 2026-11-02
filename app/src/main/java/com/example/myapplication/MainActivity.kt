package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CardsScreen()
            }
        }
    }
}

@Composable
fun CardsScreen() {

    val initialCount = integerResource(
        R.integer.initial_count
    )

    var count by rememberSaveable {
        mutableIntStateOf(initialCount)
    }

    val configuration = LocalConfiguration.current

    val columns = if (
        configuration.orientation ==
        Configuration.ORIENTATION_LANDSCAPE
    ) {
        integerResource(R.integer.landscape_columns)
    } else {
        integerResource(R.integer.portrait_columns)
    }

    val screenPadding = dimensionResource(
        R.dimen.screen_padding
    )

    val cardSpacing = dimensionResource(
        R.dimen.card_spacing
    )

    val buttonSpacing = dimensionResource(
        R.dimen.button_spacing
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(R.color.screen_background)
            )
            .safeDrawingPadding()
            .padding(screenPadding)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                cardSpacing
            ),
            verticalArrangement = Arrangement.spacedBy(
                cardSpacing
            ),
            contentPadding = PaddingValues(
                bottom = dimensionResource(
                    R.dimen.list_bottom_padding
                )
            )
        ) {
            items(count) { index ->
                NumberCard(index)
            }
        }

        Button(
            onClick = {
                count++
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = buttonSpacing)
                .height(
                    dimensionResource(R.dimen.button_height)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(
                    R.color.button_color
                )
            )
        ) {
            Text(
                text = stringResource(R.string.add_button)
            )
        }
    }
}

@Composable
fun NumberCard(index: Int) {

    val cardColor = if (index % 2 == 0) {
        colorResource(R.color.card_red)
    } else {
        colorResource(R.color.card_blue)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(
                color = cardColor,
                shape = RoundedCornerShape(
                    dimensionResource(R.dimen.card_radius)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = index.toString(),
            color = colorResource(R.color.card_text),
            fontSize = integerResource(
                R.integer.card_text_sp
            ).sp
        )
    }
}
