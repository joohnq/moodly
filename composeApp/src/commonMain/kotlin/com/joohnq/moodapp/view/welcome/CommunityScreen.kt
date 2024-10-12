package com.joohnq.moodapp.view.welcome

import androidx.compose.runtime.Composable
import com.joohnq.moodapp.CustomColors
import com.joohnq.moodapp.CustomDrawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.community_first_title
import moodapp.composeapp.generated.resources.community_span
import moodapp.composeapp.generated.resources.four
import org.jetbrains.compose.resources.stringResource

@Composable
fun CommunityScreen(onNext: () -> Unit) {
    MockScreen(
        image = CustomDrawables.Images.WelcomeCommunityImage,
        step = stringResource(Res.string.four),
        index = 4,
        backgroundColor = CustomColors.Purple30,
        firstTitle = stringResource(Res.string.community_first_title),
        span = stringResource(Res.string.community_span),
        spanColor = CustomColors.Purple40,
        onNext = onNext
    )
}