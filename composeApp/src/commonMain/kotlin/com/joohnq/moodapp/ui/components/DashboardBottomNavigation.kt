package com.joohnq.moodapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.joohnq.moodapp.ui.presentation.add_journaling_screen.AddJournalingScreen
import com.joohnq.moodapp.ui.presentation.add_stats.AddStatScreen
import com.joohnq.moodapp.ui.presentation.home.HomeScreen
import com.joohnq.moodapp.ui.presentation.journaling.JournalingScreen
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.Drawables
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.add
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
fun DashboardBottomNavigation(
    onNavigateScreen: (Screen) -> Unit
) {
    val tabNavigator = LocalTabNavigator.current

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
        Box(
            modifier = Modifier.padding(bottom = 80.dp).background(color = Colors.Transparent)
        ) {
            Button(
                contentPadding = PaddingValues(0.dp),
                onClick = { onNavigateScreen(if (tabNavigator.current.key == HomeScreen().key) AddStatScreen() else AddJournalingScreen()) },
                modifier = Modifier.size(64.dp)
                    .background(color = Colors.Green50, shape = Dimens.Shape.Circle),
                colors = ComponentColors.Button.BottomNavigationAddButtonColors()
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.Add),
                    contentDescription = stringResource(Res.string.add),
                    modifier = Modifier.size(Dimens.Icon),
                )
            }
        }
    }
}