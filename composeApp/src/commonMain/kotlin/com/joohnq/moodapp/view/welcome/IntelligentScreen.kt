package com.joohnq.moodapp.view.welcome

import androidx.compose.runtime.Composable
import com.joohnq.moodapp.CustomColors
import com.joohnq.moodapp.CustomDrawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.intelligent_first_second_title
import moodapp.composeapp.generated.resources.intelligent_first_span
import moodapp.composeapp.generated.resources.two
import org.jetbrains.compose.resources.stringResource

@Composable
fun IntelligentScreen(onNext: () -> Unit) {
    MockScreen(
        image = CustomDrawables.Images.WelcomeIntelligentImage,
        step = stringResource(Res.string.two),
        index = 2,
        backgroundColor = CustomColors.Orange20,
        secondTitle = stringResource(Res.string.intelligent_first_second_title),
        span = stringResource(Res.string.intelligent_first_span),
        spanColor = CustomColors.Orange50,
        onNext = onNext
    )
}