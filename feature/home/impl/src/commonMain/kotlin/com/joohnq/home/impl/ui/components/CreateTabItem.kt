package com.joohnq.home.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.navigation.Destination
import com.joohnq.ui.entity.BottomItem

@Composable
fun <T : Destination> CreateTabItem(
    item: BottomItem<T>,
    isCurrentRoute: (Destination) -> Boolean,
    onNavigate: (Destination) -> Unit,
) = TabItem(
    icon = item.icon,
    selected = isCurrentRoute(item.route),
    onNavigate = { onNavigate(item.route) }
)
