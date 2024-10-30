package com.joohnq.moodapp.view.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.joohnq.moodapp.view.Screens
import com.joohnq.moodapp.view.onboarding.ExpressionAnalysisScreen
import com.joohnq.moodapp.view.onboarding.MedicationsSupplementsScreen
import com.joohnq.moodapp.view.onboarding.MoodRateScreen
import com.joohnq.moodapp.view.onboarding.PhysicalSymptomsScreen
import com.joohnq.moodapp.view.onboarding.ProfessionalHelpScreen
import com.joohnq.moodapp.view.onboarding.SleepQualityScreen
import com.joohnq.moodapp.view.onboarding.StressRateScreen
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController,
    moodsViewModel: MoodsViewModel,
    userViewModel: UserViewModel,
    userPreferenceViewModel: UserPreferenceViewModel
) {
    navigation<Screens.OnboardingGraph>(startDestination = Screens.OnboardingGraph.MoodRateScreen) {
        composable<Screens.OnboardingGraph.MoodRateScreen> {
            MoodRateScreen(navigation = navController, moodsViewModel = moodsViewModel)
        }
        composable<Screens.OnboardingGraph.ProfessionalHelpScreen> {
            ProfessionalHelpScreen(
                navigation = navController,
                userViewModel = userViewModel
            )
        }
        composable<Screens.OnboardingGraph.PhysicalSymptomsScreen> {
            PhysicalSymptomsScreen(
                navigation = navController,
                userViewModel = userViewModel
            )
        }
        composable<Screens.OnboardingGraph.SleepQualityScreen> {
            SleepQualityScreen(
                navigation = navController,
                moodsViewModel = moodsViewModel
            )
        }
        composable<Screens.OnboardingGraph.MedicationsSupplementsScreen> {
            MedicationsSupplementsScreen(
                navigation = navController,
                userViewModel = userViewModel
            )
        }
        composable<Screens.OnboardingGraph.StressRateScreen> {
            StressRateScreen(
                navigation = navController,
                moodsViewModel = moodsViewModel
            )
        }
        composable<Screens.OnboardingGraph.ExpressionAnalysisScreen> {
            ExpressionAnalysisScreen(
                navigation = navController,
                moodsViewModel = moodsViewModel,
                userViewModel = userViewModel,
                userPreferencesViewModel = userPreferenceViewModel
            )
        }
    }
}