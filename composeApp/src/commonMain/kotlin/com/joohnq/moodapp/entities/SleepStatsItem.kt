package com.joohnq.moodapp.entities

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class SleepStatsItem(
    val icon: DrawableResource,
    val title: StringResource,
    val content: @Composable () -> Unit
)