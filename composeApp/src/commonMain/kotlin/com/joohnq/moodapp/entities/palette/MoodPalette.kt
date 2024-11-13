package com.joohnq.moodapp.entities.palette

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class MoodPalette(
    @Contextual val faceBackgroundColor: Color,
    @Contextual val faceColor: Color,
    @Contextual val backgroundColor: Color,
    @Contextual val subColor: Color,
    @Contextual val color: Color,
    @Contextual val moodScreenBackgroundColor: Color,
    @Contextual val moodScreenInactiveColor: Color,
    @Contextual val moodScreenTraceColor: Color,
    @Contextual val moodScreenMoodFaceBackgroundColor: Color,
    @Contextual val moodScreenMoodFaceColor: Color,
    @Contextual val barColor: Color,
    @Contextual val barFaceColor: Color,
)