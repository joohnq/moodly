package com.joohnq.ui.entity

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class IconResource(
    val icon: DrawableResource,
    val tint: Color = Color.Unspecified,
    val modifier: Modifier = Modifier,
    val contentDescription: StringResource,
)