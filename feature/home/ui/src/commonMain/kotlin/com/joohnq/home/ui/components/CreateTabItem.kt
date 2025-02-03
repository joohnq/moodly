package com.joohnq.home.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.entity.BottomItem
import com.joohnq.navigation.Destination

@Composable
fun <T : Destination> CreateTabItem(
    item: BottomItem<T>,
    isCurrentRoute: (Destination) -> Boolean,
    onNavigate: (Destination) -> Unit
) = TabItem(
    icon = item.icon,
    selected = isCurrentRoute(item.route),
    onNavigate = { onNavigate(item.route) }
)