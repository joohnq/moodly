package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.splash.ui.SplashScreen

fun NavGraphBuilder.splashNavigation(
    onNavigate: (Destination, Boolean) -> Unit,
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Loading>(startDestination = Destination.Loading) {
        composable<Destination.Loading> {
            SplashScreen(
                navigateToWelcome = {
                    onNavigateGraph(NavigationGraph.Welcome, true)
                },
                navigateToOnboarding = {
                    onNavigateGraph(NavigationGraph.Onboarding, true)
                },
                navigateToAuth = {
                    onNavigateGraph(NavigationGraph.Auth, true)
                },
                navigateToSecurity = {
                    onNavigateGraph(NavigationGraph.Security, true)
                },
                navigateToDashboard = {
                    onNavigateGraph(NavigationGraph.App, true)
                },
                navigateToCorruptedSecurity = {
                    onNavigate(Destination.Security.CorruptedSecurity, true)
                },
                navigateToUnlock = {
                    onNavigate(Destination.Security.Unlock, true)
                },
            )
        }
    }
}