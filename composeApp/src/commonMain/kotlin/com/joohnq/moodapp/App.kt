package com.joohnq.moodapp

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.joohnq.auth.ui.viewmodel.AuthContract
import com.joohnq.auth.ui.viewmodel.AuthViewModel
import com.joohnq.moodapp.navigation.appNavigation
import com.joohnq.moodapp.navigation.authNavigation
import com.joohnq.moodapp.navigation.onboardingNavigation
import com.joohnq.moodapp.navigation.securityNavigation
import com.joohnq.moodapp.navigation.splashNavigation
import com.joohnq.moodapp.navigation.welcomeNavigation
import com.joohnq.navigation.NavigationGraph
import com.joohnq.navigation.onGoBack
import com.joohnq.navigation.onNavigate
import com.joohnq.navigation.onNavigateBack
import com.joohnq.navigation.onNavigateGraph
import com.joohnq.navigation.onReplace
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
fun App() {
    KoinContext {
        val authViewModel = koinInject<AuthViewModel>()

        LaunchedEffect(Unit) {
            GoogleAuthProvider.create(
                credentials = GoogleAuthCredentials(
                    serverId = googleAuthCredentialKey
                )
            )
        }

        LaunchedEffect(Unit) {
            authViewModel.onIntent(AuthContract.Intent.GetAuthUser)
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
                splashNavigation(
                    onNavigateGraph = navHostController::onNavigateGraph,
                    onNavigate = navHostController::onNavigate,
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
                    onReplace = navHostController::onReplace,
                    onNavigateGraph = navHostController::onNavigateGraph,
                )
                securityNavigation(
                    onNavigate = navHostController::onNavigate,
                    onNavigateGraph = navHostController::onNavigateGraph,
                    onGoBack = navHostController::onGoBack
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
