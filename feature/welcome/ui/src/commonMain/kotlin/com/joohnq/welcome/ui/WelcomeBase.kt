package com.joohnq.welcome.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.ai
import com.joohnq.shared_resources.community_first_title
import com.joohnq.shared_resources.community_span
import com.joohnq.shared_resources.components.DecoratedConvexPanel
import com.joohnq.shared_resources.components.IconContinueButton
import com.joohnq.shared_resources.components.TextWithBackground
import com.joohnq.shared_resources.components.TextWithSpan
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.five
import com.joohnq.shared_resources.four
import com.joohnq.shared_resources.health_state_first_title
import com.joohnq.shared_resources.health_state_second_title
import com.joohnq.shared_resources.health_state_span
import com.joohnq.shared_resources.intelligent_first_second_title
import com.joohnq.shared_resources.intelligent_first_span
import com.joohnq.shared_resources.journaling_ai_therapy_chatbot
import com.joohnq.shared_resources.mental
import com.joohnq.shared_resources.one
import com.joohnq.shared_resources.resource_first_title
import com.joohnq.shared_resources.resource_second_title
import com.joohnq.shared_resources.resource_span
import com.joohnq.shared_resources.step
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.three
import com.joohnq.shared_resources.two
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WelcomeBase(
    state: WelcomeContract.State,
    paddingTop: Dp = 0.dp,
    onNext: () -> Unit = {}
) {
    DecoratedConvexPanel(
        panelBackgroundColor = state.backgroundColor,
        backgroundColor = Colors.White,
        panelContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = paddingTop),
                contentAlignment = Alignment.Center
            ) {
                TextWithBackground(
                    text = stringResource(Res.string.step, stringResource(state.step)),
                    borderColor = Colors.Brown80,
                    backgroundColor = Colors.Transparent,
                    textColor = Colors.Brown80
                )
            }
            Image(
                painter = painterResource(state.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(vertical = 32.dp, horizontal = 16.dp)
                    .fillMaxSize()
                    .fillMaxWidth()
                    .size(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { 0.2f * state.index },
                    modifier = Modifier.width(180.dp).height(8.dp),
                    color = Colors.Brown80,
                    trackColor = Colors.Brown20,
                    strokeCap = StrokeCap.Round,
                )
                VerticalSpacer(24.dp)
                TextWithSpan(
                    firstTitle = state.firstTitle,
                    secondTitle = state.secondTitle,
                    span = stringResource(state.span),
                    spanColor = state.spanColor,
                )
                VerticalSpacer(24.dp)
                IconContinueButton(
                    modifier = Modifier.size(60.dp),
                    colors = ComponentColors.IconButton.ContinueButtonColors(),
                    onClick = onNext
                )
            }
        }
    )
}

@Preview
@Composable
fun WelcomeBasePreview() {
    WelcomeBase(
        state = WelcomeContract.State(
            image = Drawables.Images.WelcomeHealthState,
            step = Res.string.one,
            index = 1,
            backgroundColor = Colors.Green30,
            firstTitle = Res.string.health_state_first_title,
            secondTitle = Res.string.health_state_second_title,
            span = Res.string.health_state_span,
            spanColor = Colors.Green50,
        ),
    )
}

@Preview
@Composable
fun WelcomeBasePreview2() {
    WelcomeBase(
        state = WelcomeContract.State(
            image = Drawables.Images.WelcomeIntelligent,
            step = Res.string.two,
            index = 2,
            backgroundColor = Colors.Orange20,
            secondTitle = Res.string.intelligent_first_second_title,
            span = Res.string.intelligent_first_span,
            spanColor = Colors.Orange50,
        ),
    )
}

@Preview
@Composable
fun WelcomeBasePreview3() {
    WelcomeBase(
        state = WelcomeContract.State(
            image = Drawables.Images.WelcomeMental,
            step = Res.string.three,
            index = 3,
            backgroundColor = Colors.Gray10,
            firstTitle = Res.string.ai,
            secondTitle = Res.string.journaling_ai_therapy_chatbot,
            span = Res.string.mental,
            spanColor = Colors.Gray60,
        ),
    )
}

@Preview
@Composable
fun WelcomeBasePreview4() {
    WelcomeBase(
        state = WelcomeContract.State(
            image = Drawables.Images.WelcomeResources,
            step = Res.string.four,
            index = 4,
            backgroundColor = Colors.Yellow20,
            firstTitle = Res.string.resource_first_title,
            span = Res.string.resource_span,
            secondTitle = Res.string.resource_second_title,
            spanColor = Colors.Yellow60,
        ),
    )
}

@Preview
@Composable
fun WelcomeBasePreview5() {
    WelcomeBase(
        state = WelcomeContract.State(
            image = Drawables.Images.WelcomeCommunity,
            step = Res.string.five,
            index = 5,
            backgroundColor = Colors.Purple30,
            firstTitle = Res.string.community_first_title,
            span = Res.string.community_span,
            spanColor = Colors.Purple40,
        ),
    )
}
