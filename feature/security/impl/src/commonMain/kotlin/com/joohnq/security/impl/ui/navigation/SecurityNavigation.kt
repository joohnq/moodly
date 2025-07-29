package com.joohnq.security.impl.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.security.impl.ui.presentation.pin.PinScreen
import com.joohnq.security.impl.ui.presentation.security.SecurityScreen
import com.joohnq.security.impl.ui.presentation.security_confirmed.SecurityConfirmedScreen
import com.joohnq.security.impl.ui.presentation.unlock.UnLockScreen

fun NavGraphBuilder.securityNavigation(
    onGoBack: () -> Unit,
    onNavigate: (Destination) -> Unit,
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Security>(startDestination = Destination.Security.Security) {
        composable<Destination.Security.Security> {
            SecurityScreen(
                onNavigateToDashboard = { onNavigateGraph(NavigationGraph.App, true) },
                onNavigateToSecurityConfirmed = { onNavigate(Destination.Security.SecurityConfirmed) },
                onNavigatePIN = { onNavigate(Destination.Security.PIN) }
            )
        }
        composable<Destination.Security.SecurityConfirmed> {
            SecurityConfirmedScreen(
                onNavigateToDashboard = { onNavigateGraph(NavigationGraph.App, true) }
            )
        }
        composable<Destination.Security.UnLock> {
            UnLockScreen(
                onNavigateToDashboard = {
                    onNavigateGraph(NavigationGraph.App, true)
                }
            )
        }
        composable<Destination.Security.PIN> {
            PinScreen(
                onGoBack = onGoBack,
                onNavigateToDashboard = { onNavigate(Destination.Security.SecurityConfirmed) }
            )
        }
    }
}
