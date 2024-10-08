package com.joohnq.moodapp.welcome

import androidx.compose.runtime.Composable
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables

@Composable
fun CommunityScreen(onNext: () -> Unit) {
    MockScreen(
        image = Drawables.Images.WelcomeCommunityImage,
        step = "Four",
        index = 4,
        backgroundColor = Colors.Purple30,
        firstTitle = "Loving & Supporting\n",
        span = "Community",
        spanColor = Colors.Purple40,
        onNext = onNext
    )
}