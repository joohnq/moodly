package com.joohnq.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.home.ui.event.DashboardBottomNavigationEvent
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Drawables
import org.jetbrains.compose.resources.painterResource

@Composable
fun DashboardBottomNavigation(
    items: @Composable RowScope.() -> Unit,
    onNavigateAddJournaling: () -> Unit,
    onNavigateAddStatScreen: () -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize().background(color = Colors.Transparent)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(10.dp)
                .height(100.dp)
                .paint(
                    painter = painterResource(Drawables.Shape.BottomNavigation),
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.Center,
                ),
        ) {
            items()
        }
        AddButton(
            isExpanded = isExpanded,
        ) { event ->
            when (event) {
                DashboardBottomNavigationEvent.AddHealthJournal -> onNavigateAddJournaling()
                DashboardBottomNavigationEvent.AddMood -> onNavigateAddStatScreen()
                DashboardBottomNavigationEvent.ToggleExpanded -> {
                    isExpanded = !isExpanded
                }
            }
        }
    }
}