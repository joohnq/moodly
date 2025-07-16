package com.joohnq.ui.entity

import org.jetbrains.compose.resources.StringResource

data class BottomItem<T>(
    val icon: DIcon,
    val title: StringResource,
    val route: T,
)