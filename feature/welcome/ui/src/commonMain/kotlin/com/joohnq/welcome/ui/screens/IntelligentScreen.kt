package com.joohnq.welcome.ui.screens

import androidx.compose.runtime.Composable
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.Drawables
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.intelligent_first_second_title
import com.joohnq.shared.ui.intelligent_first_span
import com.joohnq.shared.ui.two
import org.jetbrains.compose.resources.stringResource

@Composable
fun IntelligentScreen(onNext: () -> Unit) {
    MockScreen(
        image = Drawables.Images.WelcomeIntelligentImage,
        step = stringResource(Res.string.two),
        index = 2,
        backgroundColor = Colors.Orange20,
        secondTitle = stringResource(Res.string.intelligent_first_second_title),
        span = stringResource(Res.string.intelligent_first_span),
        spanColor = Colors.Orange50,
        onNext = onNext
    )
}