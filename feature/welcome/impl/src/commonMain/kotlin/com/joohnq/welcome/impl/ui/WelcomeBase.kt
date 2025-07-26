package com.joohnq.welcome.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.button.IconContinueButton
import com.joohnq.shared_resources.components.layout.DecoratedConvexPanel
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.HeadingWithSpan
import com.joohnq.shared_resources.components.text.TextWithBackground
import com.joohnq.shared_resources.step
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeBase(
    welcome: Welcome,
    paddingTop: Dp,
    onNext: () -> Unit,
) {
    DecoratedConvexPanel(
        panelBackgroundColor = welcome.backgroundColor,
        backgroundColor = Colors.White,
        panelContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = paddingTop),
                contentAlignment = Alignment.Center
            ) {
                TextWithBackground(
                    text = stringResource(Res.string.step, stringResource(welcome.step)),
                    borderColor = Colors.Brown80,
                    backgroundColor = Colors.Transparent,
                    textColor = Colors.Brown80
                )
            }
            Image(
                painter = painterResource(welcome.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        },
        content = {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(vertical = 32.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { 0.2f * welcome.index },
                    modifier = Modifier.width(180.dp).height(8.dp),
                    color = Colors.Brown80,
                    trackColor = Colors.Brown20,
                    strokeCap = StrokeCap.Round
                )
                VerticalSpacer(24.dp)
                HeadingWithSpan(
                    firstTitle = welcome.firstTitle,
                    secondTitle = welcome.secondTitle,
                    span = stringResource(welcome.span),
                    spanColor = welcome.spanColor
                )
                VerticalSpacer(24.dp)
                IconContinueButton(
                    modifier = Modifier.size(60.dp),
                    colors = ComponentColors.IconButton.mainButtonColors(),
                    onClick = onNext
                )
            }
        }
    )
}
