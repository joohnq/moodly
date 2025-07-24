package com.joohnq.ui.entity

import org.jetbrains.compose.resources.StringResource

data class BottomItem<T>(
    val icon: IconResource,
    val title: StringResource,
    val route: T,
)