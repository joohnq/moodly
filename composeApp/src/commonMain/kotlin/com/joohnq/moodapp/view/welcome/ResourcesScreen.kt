package com.joohnq.moodapp.view.welcome

import androidx.compose.runtime.Composable
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.resource_first_title
import moodapp.composeapp.generated.resources.resource_second_title
import moodapp.composeapp.generated.resources.resource_span
import moodapp.composeapp.generated.resources.three
import org.jetbrains.compose.resources.stringResource

@Composable
fun ResourcesScreen(onNext: () -> Unit) {
    MockScreen(
        image = Drawables.Images.WelcomeResourcesImage,
        step = stringResource(Res.string.three),
        index = 3,
        backgroundColor = Colors.Yellow20,
        firstTitle = stringResource(Res.string.resource_first_title),
        span = stringResource(Res.string.resource_span),
        secondTitle = stringResource(Res.string.resource_second_title),
        spanColor = Colors.Yellow60,
        onNext = onNext
    )
}