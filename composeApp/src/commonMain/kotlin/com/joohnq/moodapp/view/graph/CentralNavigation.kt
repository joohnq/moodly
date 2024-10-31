package com.joohnq.moodapp.view.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.screens.loading.CompilingDataScreen
import com.joohnq.moodapp.view.screens.loading.LoadingScreen
import com.joohnq.moodapp.view.screens.onboarding.GetUserNameScreen
import com.joohnq.moodapp.view.screens.welcome.WelcomeScreen

@Composable
fun NavHostController.CentralNavigation() {
    val navController = this
    NavHost(navController = navController, startDestination = Screens.LoadingScreen) {
        composable<Screens.LoadingScreen> {
            LoadingScreen(navigation = navController)
        }
        composable<Screens.WelcomeScreen> {
            WelcomeScreen(
                navigation = navController,
            )
        }
        onboardingNavGraph(
            navController = navController,
        )
        composable<Screens.GetUserNameScreen> {
            GetUserNameScreen(
                navigation = navController,
            )
        }
        composable<Screens.CompilingDataScreen> {
            CompilingDataScreen(
                navigation = navController,
            )
        }
        homeNavGraph()
    }
}