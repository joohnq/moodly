package com.joohnq.welcome.ui.screens

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.community_first_title
import com.joohnq.shared_resources.community_span
import com.joohnq.shared_resources.four
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