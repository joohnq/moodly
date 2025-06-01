package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.auth.ui.presentation.avatar.AvatarScreen
import com.joohnq.auth.ui.presentation.sign_in.SignInScreen
import com.joohnq.auth.ui.presentation.sign_up.SignUpScreen
import com.joohnq.auth.ui.presentation.welcome_authentication.WelcomeAuthenticationScreen
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph

fun NavGraphBuilder.authNavigation(
    onNavigate: (Destination) -> Unit,
    onReplace: (Destination, Destination) -> Unit,
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Auth>(startDestination = Destination.Auth.WelcomeAuthentication) {
        composable<Destination.Auth.WelcomeAuthentication> {
            WelcomeAuthenticationScreen(
                navigateToSignIn = { onNavigate(Destination.Auth.SignIn) },
                navigateToSignUp = { onNavigate(Destination.Auth.SignUp) },
                navigateToNext = { onNavigateGraph(NavigationGraph.Security, true) },
            )
        }
        composable<Destination.Auth.SignIn> {
            SignInScreen(
                navigateToForgotPassword = {},
                navigateToNext = { onNavigateGraph(NavigationGraph.Security, true) },
                navigateToSignUp = { onReplace(Destination.Auth.SignUp, Destination.Auth.SignIn) }
            )
        }
        composable<Destination.Auth.SignUp> {
            SignUpScreen(
                navigateToSignIn = { onReplace(Destination.Auth.SignIn, Destination.Auth.SignUp) },
                navigateToNext = { onNavigateGraph(NavigationGraph.Security, true) },
            )
        }
        composable<Destination.Auth.Avatar> {
            AvatarScreen(
                onNavigateToUserName = { onNavigate(Destination.Auth.WelcomeAuthentication) }
            )
        }
    }
}