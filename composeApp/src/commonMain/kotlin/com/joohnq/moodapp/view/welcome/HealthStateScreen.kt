package com.joohnq.moodapp.view.welcome

import androidx.compose.runtime.Composable
import com.joohnq.moodapp.CustomColors
import com.joohnq.moodapp.CustomDrawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.health_state_first_title
import moodapp.composeapp.generated.resources.health_state_second_title
import moodapp.composeapp.generated.resources.health_state_span
import moodapp.composeapp.generated.resources.one
import org.jetbrains.compose.resources.stringResource

@Composable
fun HealthStateScreen(onNext: () -> Unit) {
    MockScreen(
        image = CustomDrawables.Images.WelcomeHealthStateImage,
        step = stringResource(Res.string.one),
        index = 1,
        backgroundColor = CustomColors.Green30,
        firstTitle = stringResource(Res.string.health_state_first_title),
        secondTitle = stringResource(Res.string.health_state_second_title),
        span = stringResource(Res.string.health_state_span),
        spanColor = CustomColors.Green50,
        onNext = onNext
    )
}