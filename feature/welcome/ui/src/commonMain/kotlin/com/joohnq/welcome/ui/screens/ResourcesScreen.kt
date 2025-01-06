package com.joohnq.welcome.ui.screens

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.resource_first_title
import com.joohnq.shared_resources.resource_second_title
import com.joohnq.shared_resources.resource_span
import com.joohnq.shared_resources.three
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