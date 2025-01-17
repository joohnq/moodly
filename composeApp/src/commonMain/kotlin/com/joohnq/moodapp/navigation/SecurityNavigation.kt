package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.security.ui.presentation.security.SecurityScreen
import com.joohnq.security.ui.presentation.security_confirmed.SecurityConfirmedScreen

fun NavGraphBuilder.securityNavigation(
    onNavigate: (Destination) -> Unit,
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Security>(startDestination = Destination.Security.Security) {
        composable<Destination.Security.Security> {
            SecurityScreen(
                onNavigateToDashboard = { onNavigateGraph(NavigationGraph.App, true) },
                onNavigateToSecurityConfirmed = { onNavigate(Destination.Security.SecurityConfirmed) }
            ).Content()
        }
        composable<Destination.Security.SecurityConfirmed> {
            SecurityConfirmedScreen(
                onNavigateToDashboard = { onNavigateGraph(NavigationGraph.App, true) }
            ).Content()
        }
    }
}