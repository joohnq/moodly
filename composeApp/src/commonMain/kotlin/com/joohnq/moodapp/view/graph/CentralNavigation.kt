package com.joohnq.moodapp.view.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.screens.getusername.GetUserNameScreen
import com.joohnq.moodapp.view.screens.loading.CompilingDataScreen
import com.joohnq.moodapp.view.screens.loading.LoadingScreen
import com.joohnq.moodapp.view.screens.welcome.WelcomeScreen

@Composable
fun NavHostController.CentralNavigation() {
    val navHostController = this
    NavHost(navController = navHostController, startDestination = Screens.LoadingScreen) {
        composable<Screens.LoadingScreen> {
            LoadingScreen(navigation = navHostController)
        }
        composable<Screens.WelcomeScreen> {
            WelcomeScreen(
                navigation = navHostController,
            )
        }
        onboardingNavGraph(
            navController = navHostController,
        )
        composable<Screens.GetUserNameScreen> {
            GetUserNameScreen(navigation = navHostController)
        }
        composable<Screens.CompilingDataScreen> {
            CompilingDataScreen(
                navigation = navHostController,
            )
        }
        mainNavGraph(navHostController = navHostController)
    }
}