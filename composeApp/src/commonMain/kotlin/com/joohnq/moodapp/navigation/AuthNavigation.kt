package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.auth.ui.presentation.user_name.UserNameScreen
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph

fun NavGraphBuilder.authNavigation(
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Auth>(startDestination = Destination.Auth) {
        composable<Destination.Auth> {
            UserNameScreen(
                onNavigateToDashboardScreen = { onNavigateGraph(NavigationGraph.App, true) }
            ).Content()
        }
    }
}