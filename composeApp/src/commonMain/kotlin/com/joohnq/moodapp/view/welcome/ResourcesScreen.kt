package com.joohnq.moodapp.view.welcome

import androidx.compose.runtime.Composable
import com.joohnq.moodapp.CustomColors
import com.joohnq.moodapp.CustomDrawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.resource_first_title
import moodapp.composeapp.generated.resources.resource_second_title
import moodapp.composeapp.generated.resources.resource_span
import moodapp.composeapp.generated.resources.three
import org.jetbrains.compose.resources.stringResource

@Composable
fun ResourcesScreen(onNext: () -> Unit) {
    MockScreen(
        image = CustomDrawables.Images.WelcomeResourcesImage,
        step = stringResource(Res.string.three),
        index = 3,
        backgroundColor = CustomColors.Yellow20,
        firstTitle = stringResource(Res.string.resource_first_title),
        span = stringResource(Res.string.resource_span),
        secondTitle = stringResource(Res.string.resource_second_title),
        spanColor = CustomColors.Yellow60,
        onNext = onNext
    )
}