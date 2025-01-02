package com.joohnq.home.ui.components

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.AddJournalingScreen
import com.joohnq.health_journal.ui.presentation.journaling.JournalingScreen
import com.joohnq.home.ui.event.DashboardBottomNavigationEvent
import com.joohnq.home.ui.presentation.home.HomeScreen
import com.joohnq.shared.ui.components.BottomNavigationActionButton
import com.joohnq.shared.ui.components.BottomNavigationAddButton
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.ComponentColors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.mood.ui.presentation.add_stats.AddStatScreen
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.add_health_journal
import com.joohnq.shared.ui.add_mood
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun RowScope.TabItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        modifier = Modifier.size(48.dp),
        selected = tabNavigator.current.key == tab.key,
        colors = ComponentColors.NavigationBarItem.NavigationBarItemColors(),
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title,
                    modifier = Modifier.size(Dimens.Icon)
                )
            }
        },
    )
}

@Composable
fun AddButton(
    isExpanded: Boolean,
    onEvent: (DashboardBottomNavigationEvent) -> Unit,
) {
    val pxToMove = with(LocalDensity.current) {
        -40.dp.toPx().roundToInt()
    }
    val offset by animateIntOffsetAsState(
        targetValue = if (isExpanded) IntOffset(0, pxToMove) else IntOffset.Zero,
        label = "offset"
    )
    Row(
        modifier = Modifier.offset { offset }.padding(bottom = 80.dp)
            .background(color = Colors.Green50, shape = Dimens.Shape.Circle)
            .padding(if (isExpanded) 5.dp else 0.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (isExpanded)
            BottomNavigationActionButton(
                onClick = { onEvent(DashboardBottomNavigationEvent.AddMood) },
                drawable = Drawables.Icons.MoodNeutral,
                description = Res.string.add_mood
            )
        BottomNavigationAddButton(
            isExpanded = isExpanded,
            onClick = { onEvent(DashboardBottomNavigationEvent.ToggleExpanded) }
        )
        if (isExpanded)
            BottomNavigationActionButton(
                onClick = { onEvent(DashboardBottomNavigationEvent.AddHealthJournal) },
                drawable = Drawables.Icons.Document,
                description = Res.string.add_health_journal
            )
    }
}

@Composable
fun DashboardBottomNavigation(
    onNavigateScreen: (Screen) -> Unit,
) {
    var isExpanded by remember { mutableStateOf(false) }

    fun onNavigate(screen: Screen) {
        isExpanded = false
        onNavigateScreen(screen)
    }

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
            TabItem(HomeScreen())
            TabItem(JournalingScreen())
        }
        AddButton(
            isExpanded = isExpanded,
        ) { event ->
            when (event) {
                DashboardBottomNavigationEvent.AddHealthJournal ->
                    onNavigate(AddJournalingScreen())

                DashboardBottomNavigationEvent.AddMood ->
                    onNavigate(AddStatScreen())

                DashboardBottomNavigationEvent.ToggleExpanded -> {
                    isExpanded = !isExpanded
                }
            }
        }
    }
}