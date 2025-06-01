package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.welcome.ui.WelcomeScreen

fun NavGraphBuilder.welcomeNavigation(
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Welcome>(startDestination = Destination.Welcome) {
        composable<Destination.Welcome> {
            WelcomeScreen(
                onNavigateToOnboarding = { onNavigateGraph(NavigationGraph.Onboarding, true) }
            )
        }
    }
}