package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.loading.ui.presentation.loading.LoadingScreen
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph

fun NavGraphBuilder.loadingNavigation(
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Loading>(startDestination = Destination.Loading) {
        composable<Destination.Loading> {
            LoadingScreen(
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
            ).Content()
        }
    }
}