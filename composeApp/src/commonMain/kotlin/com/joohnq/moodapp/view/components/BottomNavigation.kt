package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.joohnq.moodapp.entities.BottomScreens
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.Drawables
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NavController.BottomNavigation(
    onNavigateToAddMood: () -> Unit,
    onNavigateToRoute: (Screens) -> Unit
) {
    val navBackStackEntry by currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val hierarchy = currentDestination?.hierarchy
    val bottomScreens = remember {
        listOf(
            BottomScreens.Home,
            BottomScreens.Journaling,
        )
    }
    if (hierarchy?.any {
            bottomScreens.map { screen -> screen.route::class.qualifiedName }.contains(it.route)
        } == true
    )
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(10.dp).height(80.dp)
                    .paint(
                        painter = painterResource(Drawables.Shape.BottomNavigation),
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.Center
                    ),
            ) {
                bottomScreens.forEach { screen ->
                    val isSelected =
                        hierarchy.any { it.route == screen.route::class.qualifiedName }
                    NavigationBarItem(
                        selected = isSelected,
                        colors = ComponentColors.NavigationBarItem.NavigationBarItemColors(),
                        onClick = { onNavigateToRoute(screen.route) },
                        icon = {
                            Icon(
                                painter = painterResource(screen.icon),
                                contentDescription = stringResource(screen.name),
                                modifier = Modifier.size(Dimens.Icon)
                            )
                        },
                    )
                }
            }
            Box(
                modifier = Modifier.padding(bottom = 60.dp).background(color = Colors.Transparent)
            ) {
                with(BottomScreens.Add) {
                    Button(
                        contentPadding = PaddingValues(0.dp),
                        onClick = onNavigateToAddMood,
                        modifier = Modifier.size(64.dp)
                            .background(color = Colors.Green50, shape = Dimens.Shape.Circle),
                        colors = ComponentColors.Button.BottomNavigationAddButtonColors()
                    ) {
                        Icon(
                            painter = painterResource(icon),
                            contentDescription = stringResource(name),
                            modifier = Modifier.size(Dimens.Icon),
                        )
                    }
                }
            }
        }
}