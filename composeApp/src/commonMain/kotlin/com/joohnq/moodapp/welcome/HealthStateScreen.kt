package com.joohnq.moodapp.welcome

import androidx.compose.runtime.Composable
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables

@Composable
fun HealthStateScreen(onNext: () -> Unit) {
    MockScreen(
        image = Drawables.Images.WelcomeHealthStateImage,
        step = "One",
        index = 1,
        backgroundColor = Colors.Green30,
        firstTitle = "Personalize Your Mental\n",
        secondTitle = "With AI",
        span = "Health State ",
        spanColor = Colors.Green50,
        onNext = onNext
    )
}