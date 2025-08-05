package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.home.impl.ui.components.DashboardBottomNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    DashboardBottomNavigation(
        isCurrentRoute = { true },
        isCentralExpanded = false
    )
}
