package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.auth.ui.presentation.avatar.AvatarScreen
import com.joohnq.auth.ui.presentation.user_name.UserNameScreen
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph

fun NavGraphBuilder.authNavigation(
    onNavigate: (Destination) -> Unit,
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Auth>(startDestination = Destination.Auth.Avatar) {
        composable<Destination.Auth.Avatar> {
            AvatarScreen(
                onNavigateToUserName = { onNavigate(Destination.Auth.UserName) }
            ).Content()
        }
        composable<Destination.Auth.UserName> {
            UserNameScreen(
                onNavigateToDashboardScreen = { onNavigateGraph(NavigationGraph.App, true) }
            ).Content()
        }
    }
}