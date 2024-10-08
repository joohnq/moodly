package com.joohnq.moodapp.welcome

import androidx.compose.runtime.Composable
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables

@Composable
fun ResourcesScreen(onNext: () -> Unit) {
    MockScreen(
        image = Drawables.Images.WelcomeResourcesImage,
        step = "Three",
        index = 3,
        backgroundColor = Colors.Yellow20,
        firstTitle = "Mindful ",
        span = "Resources ",
        secondTitle = "That Makes \n You Happy",
        spanColor = Colors.Yellow60,
        onNext = onNext
    )
}