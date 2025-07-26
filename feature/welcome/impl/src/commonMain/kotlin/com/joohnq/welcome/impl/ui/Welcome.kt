package com.joohnq.welcome.impl.ui

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class Welcome(
    val image: DrawableResource,
    val step: StringResource,
    val index: Int,
    val backgroundColor: Color,
    val firstTitle: StringResource? = null,
    val span: StringResource,
    val secondTitle: StringResource? = null,
    val spanColor: Color
)