package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.user.ui.presentation.get_user_name.GetUserNameScreen

fun NavGraphBuilder.authNavigation(
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Auth>(startDestination = Destination.Auth) {
        composable<Destination.Auth> {
            GetUserNameScreen(
                onNavigateToDashboardScreen = { onNavigateGraph(NavigationGraph.App, true) }
            ).Content()
        }
    }
}