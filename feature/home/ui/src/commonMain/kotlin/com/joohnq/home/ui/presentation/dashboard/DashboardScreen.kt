package com.joohnq.home.ui.presentation.dashboard

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.joohnq.health_journal.ui.presentation.journaling.JournalingScreen
import com.joohnq.home.ui.BottomItem
import com.joohnq.home.ui.components.DashboardBottomNavigation
import com.joohnq.home.ui.components.TabItem
import com.joohnq.home.ui.presentation.home.HomeScreen
import com.joohnq.navigation.Destination
import com.joohnq.core.ui.entity.DIcon
import com.joohnq.shared_resources.CustomScreenNoUI
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.home
import com.joohnq.shared_resources.journaling
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables

class DashboardScreen(
    private val onNavigateAddJournaling: () -> Unit,
    private val onNavigateAddStatScreen: () -> Unit,
    private val onNavigateFreudScore: () -> Unit,
    private val onNavigateToMood: () -> Unit,
    private val onNavigateToHealthJournal: () -> Unit,
    private val onNavigateToMindfulJournal: () -> Unit,
    private val onNavigateToSleepQuality: () -> Unit,
    private val onNavigateToStressLevel: () -> Unit,
    private val onNavigateToEditJournaling: (Int) -> Unit,
    private val onNavigateToAllJournals: () -> Unit,
) : CustomScreenNoUI() {
    @Composable
    override fun Screen() {
        val navigator = rememberNavController()
        val navBackStackEntry by navigator.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        fun onNavigate(destination: Destination) {
            navigator.navigate(destination)
        }

        val bottomItems = listOf(
            BottomItem(
                icon = DIcon(
                    icon = Drawables.Icons.Home,
                    tint = Colors.Brown80,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.home
                ),
                title = Res.string.home,
                route = Destination.App.DashBoard.Home
            ),
            BottomItem(
                icon = DIcon(
                    icon = Drawables.Icons.Document,
                    tint = Colors.Brown80,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.journaling
                ),
                title = Res.string.journaling,
                route = Destination.App.DashBoard.Journaling,
            )
        )

        Scaffold(
            bottomBar = {
                DashboardBottomNavigation(
                    onNavigateAddJournaling = onNavigateAddJournaling,
                    onNavigateAddStatScreen = onNavigateAddStatScreen,
                    items = {
                        bottomItems.forEach { item ->
                            val isSelected =
                                currentDestination?.hierarchy?.any { it.route == item.route::class.qualifiedName } == true
                            TabItem(
                                icon = item.icon,
                                selected = isSelected,
                                onNavigate = { onNavigate(item.route) }
                            )
                        }
                    }
                )
            }
        ) { _ ->
            NavHost(navigator, startDestination = Destination.App.DashBoard.Home) {
                composable<Destination.App.DashBoard.Home> {
                    HomeScreen(
                        onNavigateFreudScore = onNavigateFreudScore,
                        onNavigateToMood = onNavigateToMood,
                        onNavigateToHealthJournal = onNavigateToHealthJournal,
                        onNavigateToMindfulJournal = onNavigateToMindfulJournal,
                        onNavigateToSleepQuality = onNavigateToSleepQuality,
                        onNavigateToStressLevel = onNavigateToStressLevel
                    )
                }
                composable<Destination.App.DashBoard.Journaling> {
                    JournalingScreen(
                        onNavigateToEditJournaling = onNavigateToEditJournaling,
                        onNavigateToAllJournals = onNavigateToAllJournals
                    ).Content()
                }
            }
        }
    }
}