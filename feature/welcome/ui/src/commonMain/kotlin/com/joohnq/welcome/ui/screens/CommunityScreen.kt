package com.joohnq.welcome.ui.screens

import androidx.compose.runtime.Composable
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.Drawables
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.community_first_title
import com.joohnq.shared.ui.community_span
import com.joohnq.shared.ui.four
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