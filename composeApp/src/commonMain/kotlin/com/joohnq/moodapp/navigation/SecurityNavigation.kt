package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.security.ui.presentation.biometric_faceid.BiometricFaceIdScreen
import com.joohnq.security.ui.presentation.pin.PINScreen
import com.joohnq.security.ui.presentation.security.SecurityScreen
import com.joohnq.security.ui.presentation.security_confirmed.SecurityConfirmedScreen

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
                onNavigatePIN = { onNavigate(Destination.Security.PIN) },
            )
        }
        composable<Destination.Security.SecurityConfirmed> {
            SecurityConfirmedScreen(
                onNavigateToDashboard = { onNavigateGraph(NavigationGraph.App, true) }
            )
        }

        composable<Destination.Security.BiometricFaceId> {
            BiometricFaceIdScreen(
                onNavigateToDashboard = { onNavigateGraph(NavigationGraph.App, true) }
            )
        }
        composable<Destination.Security.PIN> {
            PINScreen(
                onGoBack = onGoBack,
                onNavigateToDashboard = { onNavigate(Destination.Security.SecurityConfirmed) }
            )
        }
        composable<Destination.Security.CorruptedSecurity> {
//            CorruptedSecurityScreen(
//                onGoBack = onGoBack,
//                onNavigateToDashboard = { onNavigate(Destination.Security.SecurityConfirmed) }
//            )
        }
    }
}