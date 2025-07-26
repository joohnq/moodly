package com.joohnq.sleep_quality.impl.ui.entity

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class SleepStatsItem(
    val icon: DrawableResource,
    val title: StringResource,
    val content: @Composable () -> Unit,
)
