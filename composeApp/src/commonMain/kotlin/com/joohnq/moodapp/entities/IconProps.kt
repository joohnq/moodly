package com.joohnq.moodapp.entities

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class IconProps(
    val icon: DrawableResource,
    val tint: Color = Color.Unspecified,
    val modifier: Modifier = Modifier,
    val contentDescription: String? = null
)