package com.joohnq.moodapp

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.moodapp.navigation.appNavigation
import com.joohnq.moodapp.navigation.authNavigation
import com.joohnq.moodapp.navigation.loadingNavigation
import com.joohnq.moodapp.navigation.onboardingNavigation
import com.joohnq.moodapp.navigation.securityNavigation
import com.joohnq.moodapp.navigation.welcomeNavigation
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModelIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModelIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        val userViewModel: UserViewModel = sharedViewModel()
        val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()

        SideEffect {
            userPreferencesViewModel.onAction(UserPreferenceViewModelIntent.AddUserPreferences)
            userViewModel.onAction(UserViewModelIntent.InitUser)
        }

        fun NavHostController.onNavigate(destination: Destination, finish: Boolean = false) {
            navigate(destination) {
                if (finish) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

        fun NavHostController.onNavigateBack(destination: Destination) {
            popBackStack(destination, inclusive = false)
        }

        fun NavHostController.onNavigateGraph(graph: NavigationGraph, finish: Boolean = false) {
            navigate(graph) {
                if (finish) {
                    popUpTo(0) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

        fun NavHostController.onGoBack() {
            popBackStack()
        }

        MaterialTheme {
            val navHostController = rememberNavController()
            NavHost(
                navController = navHostController,
                startDestination = NavigationGraph.Loading,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popExitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
            ) {
                loadingNavigation(
                    onNavigateGraph = navHostController::onNavigateGraph
                )
                welcomeNavigation(
                    onNavigateGraph = navHostController::onNavigateGraph
                )
                onboardingNavigation(
                    onNavigate = navHostController::onNavigate,
                    onNavigateGraph = navHostController::onNavigateGraph,
                    onGoBack = navHostController::onGoBack
                )
                authNavigation(
                    onNavigate = navHostController::onNavigate,
                )
                securityNavigation(
                    onNavigate = navHostController::onNavigate,
                    onNavigateGraph = navHostController::onNavigateGraph
                )
                appNavigation(
                    onNavigate = navHostController::onNavigate,
                    onNavigateBack = navHostController::onNavigateBack,
                    onGoBack = navHostController::onGoBack,
                )
            }
        }
    }
}