package com.joohnq.moodapp.entities.palette

import androidx.compose.ui.graphics.Color

data class MoodPalette(
    val faceBackgroundColor: Color,
    val faceColor: Color,
    val backgroundColor: Color,
    val subColor: Color,
    val color: Color,
    val moodScreenBackgroundColor: Color,
    val moodScreenInactiveColor: Color,
    val moodScreenTraceColor: Color,
    val moodScreenMoodFaceBackgroundColor: Color,
    val moodScreenMoodFaceColor: Color,
    val barColor: Color,
    val barFaceColor: Color,
)