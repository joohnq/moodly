package com.joohnq.moodapp.entities.palette

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class StressLevelPalette(
    @Contextual val color: Color,
    @Contextual val backgroundColor: Color,
)
