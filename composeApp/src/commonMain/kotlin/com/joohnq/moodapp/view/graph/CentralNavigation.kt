package com.joohnq.moodapp.view.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joohnq.moodapp.view.screens.CompilingDataScreen
import com.joohnq.moodapp.view.screens.GetUserNameScreen
import com.joohnq.moodapp.view.screens.LoadingScreen
import com.joohnq.moodapp.view.screens.WelcomeScreen
import com.joohnq.moodapp.view.screens.loading.CompilingDataScreen
import com.joohnq.moodapp.view.screens.loading.LoadingScreen
import com.joohnq.moodapp.view.screens.onboarding.GetUserNameScreen
import com.joohnq.moodapp.view.screens.welcome.WelcomeScreen

@Composable
fun NavHostController.CentralNavigation() {
    val navHostController = this
    NavHost(navController = navHostController, startDestination = LoadingScreen) {
        composable<LoadingScreen> {
            LoadingScreen(navigation = navHostController)
        }
        composable<WelcomeScreen> {
            WelcomeScreen(
                navigation = navHostController,
            )
        }
        onboardingNavGraph(
            navController = navHostController,
        )
        composable<GetUserNameScreen> {
            GetUserNameScreen(
                navigation = navHostController,
            )
        }
        composable<CompilingDataScreen> {
            CompilingDataScreen(
                navigation = navHostController,
            )
        }
        mainNavGraph(navHostController = navHostController)
    }
}