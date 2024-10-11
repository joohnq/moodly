package com.joohnq.moodapp.welcome

import androidx.compose.runtime.Composable
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.community_first_title
import moodapp.composeapp.generated.resources.community_span
import moodapp.composeapp.generated.resources.four
import org.jetbrains.compose.resources.stringResource

@Composable
fun CommunityScreen(onNext: () -> Unit) {
    MockScreen(
        image = Drawables.Images.WelcomeCommunityImage,
        step = stringResource(Res.string.four),
        index = 4,
        backgroundColor = Colors.Purple30,
        firstTitle = stringResource(Res.string.community_first_title),
        span = stringResource(Res.string.community_span),
        spanColor = Colors.Purple40,
        onNext = onNext
    )
}