package com.joohnq.welcome.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.joohnq.shared.ui.ScreenDimensions
import com.joohnq.shared.ui.components.IconContinueButton
import com.joohnq.shared.ui.components.TextWithBackground
import com.joohnq.shared.ui.components.TextWithSpan
import com.joohnq.shared.ui.components.VerticalSpacer
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.ComponentColors
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.step
import com.joohnq.welcome.ui.WelcomeScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun MockScreen(
    image: DrawableResource,
    step: String,
    index: Int,
    backgroundColor: Color,
    firstTitle: String? = null,
    span: String,
    secondTitle: String? = null,
    spanColor: Color,
    onNext: () -> Unit,
) {
    val screenDimensions: ScreenDimensions = koinInject()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = screenDimensions.statusBarHeight.dp),
                contentAlignment = Alignment.Center
            ) {
                TextWithBackground(
                    text = stringResource(Res.string.step, step),
                    borderColor = Colors.Brown80,
                    backgroundColor = Colors.Transparent,
                    textColor = Colors.Brown80
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.45f)
                    .background(
                        color = Colors.White,
                    )
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { 0.25f * index },
                    modifier = Modifier.width(180.dp).height(8.dp),
                    color = Colors.Brown80,
                    trackColor = Colors.Brown20,
                    strokeCap = StrokeCap.Round,
                )
                VerticalSpacer(24.dp)
                TextWithSpan(
                    firstTitle = firstTitle,
                    secondTitle = secondTitle,
                    span = span,
                    spanColor = spanColor,
                )
                VerticalSpacer(24.dp)
                IconContinueButton(
                    modifier = Modifier.size(60.dp)
                        .testTag(WelcomeScreen.WelcomeTestTag.GO_NEXT + index),
                    colors = ComponentColors.IconButton.ContinueButtonColors(),
                    onClick = onNext
                )
            }
        }
    }
}
