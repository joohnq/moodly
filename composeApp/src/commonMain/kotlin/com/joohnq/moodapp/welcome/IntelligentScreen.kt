package com.joohnq.moodapp.welcome

import androidx.compose.runtime.Composable
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables

@Composable
fun IntelligentScreen(onNext: () -> Unit) {
    MockScreen(
        image = Drawables.Images.WelcomeIntelligentImage,
        step = "Two",
        index = 2,
        backgroundColor = Colors.Orange20,
        secondTitle = "Mood Tracking & \n Emotion Insights",
        span = "Intelligent ",
        spanColor = Colors.Orange50,
        onNext = onNext
    )
}