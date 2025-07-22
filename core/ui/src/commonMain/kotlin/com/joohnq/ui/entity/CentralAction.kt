package com.joohnq.ui.entity

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class CentralAction<T>(
    val title: String,
    val icon: DrawableResource,
    val destination: T
)