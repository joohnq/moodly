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
import com.joohnq.core.ui.CustomScreenNoUI
import com.joohnq.core.ui.entity.DIcon
import com.joohnq.health_journal.ui.presentation.journaling.JournalingScreen
import com.joohnq.home.ui.BottomItem
import com.joohnq.home.ui.components.DashboardBottomNavigation
import com.joohnq.home.ui.components.TabItem
import com.joohnq.home.ui.presentation.home.HomeScreen
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.chat
import com.joohnq.shared_resources.home
import com.joohnq.shared_resources.journaling
import com.joohnq.shared_resources.profile
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

        fun isCurrentRoute(route: String?): Boolean =
            currentDestination?.hierarchy?.any { it.route == route } == true

        fun onNavigate(destination: Destination) {
            if (!isCurrentRoute(destination::class.qualifiedName))
                navigator.navigate(destination)
        }

        val bottomItems = listOf(
            BottomItem(
                icon = DIcon(
                    icon = Drawables.Icons.Home,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.home
                ),
                title = Res.string.home,
                route = Destination.App.DashBoard.Home
            ),
            BottomItem(
                icon = DIcon(
                    icon = Drawables.Icons.Chat,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.chat
                ),
                title = Res.string.chat,
                route = Destination.App.DashBoard.Home
            ),
            BottomItem(
                icon = DIcon(
                    icon = Drawables.Icons.Document,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.journaling
                ),
                title = Res.string.journaling,
                route = Destination.App.DashBoard.Journaling,
            ),
            BottomItem(
                icon = DIcon(
                    icon = Drawables.Icons.Profile,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.profile
                ),
                title = Res.string.profile,
                route = Destination.App.DashBoard.Journaling,
            )
        )

        @Composable
        fun BottomItem<out Destination>.createTabItem() {
            val isSelected = isCurrentRoute(route::class.qualifiedName)
            return TabItem(
                icon = icon,
                selected = isSelected,
                onNavigate = { onNavigate(route) }
            )
        }

        Scaffold(
            bottomBar = {
                DashboardBottomNavigation(
                    left = {
                        bottomItems.take(2).forEach { item ->
                            item.createTabItem()
                        }
                    },
                    right = {
                        bottomItems.takeLast(2).forEach { item ->
                            item.createTabItem()
                        }
                    },
                    onNavigateAddJournaling = onNavigateAddJournaling,
                    onNavigateAddStatScreen = onNavigateAddStatScreen,
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