package com.joohnq.home.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared.domain.entity.DIcon
import com.joohnq.shared.ui.components.Icon
import com.joohnq.shared.ui.theme.ComponentColors

@Composable
fun RowScope.TabItem(
    icon: DIcon,
    selected: Boolean,
    onNavigate: () -> Unit,
) {
    NavigationBarItem(
        modifier = Modifier.size(48.dp),
        selected = selected,
        colors = ComponentColors.NavigationBarItem.NavigationBarItemColors(),
        onClick = onNavigate,
        icon = { Icon(icon) },
    )
}