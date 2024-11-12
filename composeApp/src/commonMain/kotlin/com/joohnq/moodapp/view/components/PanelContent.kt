package com.joohnq.moodapp.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.ScreenDimensions
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.add
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun PanelContent(
    isDark: Boolean,
    padding: PaddingValues,
    text: StringResource,
    backgroundColor: Color,
    background: DrawableResource,
    color: Color,
    onAdd: () -> Unit,
    onGoBack: () -> Unit,
    topBarContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val screenDimensions: ScreenDimensions = koinInject()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenDimensions.deviceHeight() * 0.5f)
            .background(color = backgroundColor)
    ) {
        Image(
            painter = painterResource(background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(color = color),
            modifier = Modifier.fillMaxSize()
        )

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isDark) {
                TopBar(
                    modifier = Modifier
                        .padding(top = padding.calculateTopPadding())
                        .paddingHorizontalMedium(),
                    text = text,
                    onGoBack = onGoBack,
                    content = topBarContent
                )
            } else {
                TopBar(
                    modifier = Modifier
                        .padding(top = padding.calculateTopPadding())
                        .paddingHorizontalMedium(),
                    isDark = false,
                    text = text,
                    onGoBack = onGoBack
                )
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .absoluteOffset(y = 40.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            FilledIconButton(
                onClick = onAdd,
                shape = Dimens.Shape.Circle,
                colors = ComponentColors.IconButton.ContinueButtonColors(),
                modifier = Modifier
                    .size(80.dp)
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.Add),
                    contentDescription = stringResource(Res.string.add),
                    modifier = Modifier.size(Dimens.Icon)
                )
            }
        }
    }
}

