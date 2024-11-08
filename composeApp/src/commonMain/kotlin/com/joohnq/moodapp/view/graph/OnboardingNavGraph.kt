package com.joohnq.moodapp.view.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.screens.onboarding.ExpressionAnalysisScreen
import com.joohnq.moodapp.view.screens.onboarding.MedicationsSupplementsScreen
import com.joohnq.moodapp.view.screens.onboarding.MoodRateScreen
import com.joohnq.moodapp.view.screens.onboarding.PhysicalSymptomsScreen
import com.joohnq.moodapp.view.screens.onboarding.ProfessionalHelpScreen
import com.joohnq.moodapp.view.screens.onboarding.SleepQualityScreen
import com.joohnq.moodapp.view.screens.onboarding.StressLevelScreen

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController,
) {
    navigation<Screens.OnboardingGraph>(startDestination = Screens.OnboardingGraph.MoodRateScreen) {
        composable<Screens.OnboardingGraph.MoodRateScreen> {
            MoodRateScreen(navigation = navController)
        }
        composable<Screens.OnboardingGraph.ProfessionalHelpScreen> {
            ProfessionalHelpScreen(
                navigation = navController,
            )
        }
        composable<Screens.OnboardingGraph.PhysicalSymptomsScreen> {
            PhysicalSymptomsScreen(
                navigation = navController,
            )
        }
        composable<Screens.OnboardingGraph.SleepQualityScreen> {
            SleepQualityScreen(
                navigation = navController,
            )
        }
        composable<Screens.OnboardingGraph.MedicationsSupplementsScreen> {
            MedicationsSupplementsScreen(
                navigation = navController,
            )
        }
        composable<Screens.OnboardingGraph.StressLevelScreen> {
            StressLevelScreen(
                navigation = navController
            )
        }
        composable<Screens.OnboardingGraph.ExpressionAnalysisScreen> {
            ExpressionAnalysisScreen(
                navigation = navController,
            )
        }
    }
}