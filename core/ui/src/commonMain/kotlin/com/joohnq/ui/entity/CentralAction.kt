package com.joohnq.ui.entity

import org.jetbrains.compose.resources.DrawableResource

data class CentralAction<T>(
    val title: String,
    val icon: DrawableResource,
    val destination: T,
)
