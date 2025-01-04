package com.joohnq.home.ui

import com.joohnq.shared.domain.entity.DIcon
import org.jetbrains.compose.resources.StringResource

data class BottomItem<T>(
    val icon: DIcon,
    val title: StringResource,
    val route: T,
)
