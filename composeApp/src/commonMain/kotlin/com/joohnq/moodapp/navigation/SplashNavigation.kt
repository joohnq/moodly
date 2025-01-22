package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.splash.ui.presentation.splash_screen.SplashScreen

fun NavGraphBuilder.splashNavigation(
    onNavigate: (Destination, Boolean) -> Unit,
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Loading>(startDestination = Destination.Loading) {
        composable<Destination.Loading> {
            SplashScreen(
                onNavigateToWelcome = {
                    onNavigateGraph(NavigationGraph.Welcome, true)
                },
                onNavigateToOnboarding = {
                    onNavigateGraph(NavigationGraph.Onboarding, true)
                },
                onNavigateToAuth = {
                    onNavigateGraph(NavigationGraph.Auth, true)
                },
                onNavigateToSecurity = {
                    onNavigateGraph(NavigationGraph.Security, true)
                },
                onNavigateToDashboard = {
                    onNavigateGraph(NavigationGraph.App, true)
                },
                onNavigateToCorruptedSecurity = {
                    onNavigate(Destination.Security.CorruptedSecurity, true)
                },
                onNavigateToBiometricFaceId = {
                    onNavigate(Destination.Security.BiometricFaceId, true)
                },
                onNavigateToPIN = {
                    onNavigate(Destination.Security.PIN, true)
                },
            )
        }
    }
}