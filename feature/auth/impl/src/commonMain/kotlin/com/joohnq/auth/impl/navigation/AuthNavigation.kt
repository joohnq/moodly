package com.joohnq.auth.impl.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.auth.impl.presentation.avatar.AvatarScreen
import com.joohnq.auth.impl.presentation.auth.AuthNameScreen
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
            )
        }
        composable<Destination.Auth.UserName> {
            AuthNameScreen(
                onNavigateToSecurity = { onNavigateGraph(NavigationGraph.Security, true) }
            )
        }
    }
}