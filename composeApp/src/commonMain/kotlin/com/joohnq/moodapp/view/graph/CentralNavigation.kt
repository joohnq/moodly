package com.joohnq.moodapp.view.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.joohnq.moodapp.view.Screens
import com.joohnq.moodapp.view.loading.CompilingDataScreen
import com.joohnq.moodapp.view.loading.LoadingScreen
import com.joohnq.moodapp.view.onboarding.GetUserNameScreen
import com.joohnq.moodapp.view.welcome.WelcomeScreen
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel

@Composable
fun CentralNavigation(
    navController: NavHostController,
    userPreferenceViewModel: UserPreferenceViewModel,
    userViewModel: UserViewModel,
    moodsViewModel: MoodsViewModel
) {
    NavHost(navController = navController, startDestination = Screens.LoadingScreen) {
        composable<Screens.LoadingScreen> {
            LoadingScreen(navigation = navController)
        }
        composable<Screens.WelcomeScreen> {
            WelcomeScreen(
                navigation = navController,
                userPreferenceViewModel = userPreferenceViewModel
            )
        }
        onboardingNavGraph(
            navController = navController,
            moodsViewModel = moodsViewModel,
            userViewModel = userViewModel,
            userPreferenceViewModel = userPreferenceViewModel
        )
        composable<Screens.GetUserNameScreen> {
            GetUserNameScreen(
                navigation = navController,
                userViewModel = userViewModel,
                userPreferencesViewModel = userPreferenceViewModel
            )
        }
        composable<Screens.CompilingDataScreen> {
            CompilingDataScreen(
                navigation = navController,
                moodsViewModel = moodsViewModel,
                userViewModel = userViewModel,
            )
        }
        homeNavGraph(
            moodsViewModel = moodsViewModel,
            userViewModel = userViewModel,
            userPreferenceViewModel = userPreferenceViewModel
        )
    }
}