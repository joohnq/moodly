package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.home.ui.presentation.dashboard.DashboardScreen
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph

fun NavGraphBuilder.dashboardNavigation(
    onNavigate: (Destination, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Dashboard>(startDestination = Destination.Dashboard.Home) {
        composable<Destination.Dashboard.Home> {
            DashboardScreen(
                onNavigateAddJournaling = { onNavigate(Destination.Dashboard.Journaling, false) },
                onNavigateAddStatScreen = { onNavigate(Destination.Mood.AddStat, false) }
            ).Content()
        }
    }
}

