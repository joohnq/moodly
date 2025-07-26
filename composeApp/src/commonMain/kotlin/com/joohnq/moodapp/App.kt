package com.joohnq.moodapp

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.joohnq.auth.impl.ui.navigation.authNavigation
import com.joohnq.moodapp.navigation.appNavigation
import com.joohnq.navigation.NavigationGraph
import com.joohnq.navigation.onGoBack
import com.joohnq.navigation.onNavigate
import com.joohnq.navigation.onNavigateBack
import com.joohnq.navigation.onNavigateGraph
import com.joohnq.onboarding.impl.ui.navigation.onboardingNavigation
import com.joohnq.security.impl.ui.navigation.securityNavigation
import com.joohnq.splash.impl.ui.navigation.splashNavigation
import com.joohnq.welcome.impl.ui.navigation.welcomeNavigation
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        MaterialTheme {
            val navHostController = rememberNavController()
            NavHost(
                navController = navHostController,
                startDestination = NavigationGraph.Loading,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popExitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None }
            ) {
                splashNavigation(
                    onNavigateGraph = navHostController::onNavigateGraph,
                    onNavigate = navHostController::onNavigate
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
                    onNavigateGraph = navHostController::onNavigateGraph
                )
                securityNavigation(
                    onNavigate = navHostController::onNavigate,
                    onNavigateGraph = navHostController::onNavigateGraph,
                    onGoBack = navHostController::onGoBack
                )
                appNavigation(
                    onNavigate = navHostController::onNavigate,
                    onNavigateBack = navHostController::onNavigateBack,
                    onGoBack = navHostController::onGoBack
                )
            }
        }
    }
}
