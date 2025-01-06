package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.moodapp.presentation.loading.LoadingScreen
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
                onNavigateToGetUserName = {
                    onNavigateGraph(NavigationGraph.Auth, true)
                },
                onNavigateToDashboard = {
                    onNavigateGraph(NavigationGraph.App, true)
                }
            ).Content()
        }
    }
}