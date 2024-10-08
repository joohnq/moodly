package com.joohnq.moodapp

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class IconProps(
    val icon: DrawableResource,
    val tint: Color,
    val modifier: Modifier,
    val contentDescription: String? = null
)