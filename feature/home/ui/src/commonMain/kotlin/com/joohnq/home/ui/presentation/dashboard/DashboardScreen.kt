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
import com.joohnq.shared.domain.entity.DIcon
import com.joohnq.shared.ui.CustomScreenNoUI
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.home
import com.joohnq.shared.ui.journaling
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.shared.ui.theme.Drawables

class DashboardScreen(
    private val onNavigateAddJournaling: () -> Unit,
    private val onNavigateAddStatScreen: () -> Unit,
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
                route = Destination.Dashboard.Home
            ),
            BottomItem(
                icon = DIcon(
                    icon = Drawables.Icons.Document,
                    tint = Colors.Brown80,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.journaling
                ),
                title = Res.string.journaling,
                route = Destination.Dashboard.Journaling,
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
                                onNavigate = { onNavigate(Destination.Dashboard.Home) }
                            )
                        }
                    }
                )
            }
        ) { _ ->
            NavHost(navigator, startDestination = Destination.Dashboard.Home) {
                composable<Destination.Dashboard.Home> { HomeScreen().Content() }
                composable<Destination.Dashboard.Journaling> { JournalingScreen().Content() }
            }
        }
    }
}