package com.joohnq.moodapp.entities.palette

import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class SleepQualityPalette(
    @Contextual val backgroundColor2: Color,
    @Contextual val backgroundColor: Color,
    @Contextual val color: Color,
)