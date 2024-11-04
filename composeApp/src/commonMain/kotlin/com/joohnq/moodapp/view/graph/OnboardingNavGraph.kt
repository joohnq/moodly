package com.joohnq.moodapp.view.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.joohnq.moodapp.view.screens.OnboardingGraph
import com.joohnq.moodapp.view.screens.onboarding.ExpressionAnalysisScreen
import com.joohnq.moodapp.view.screens.onboarding.MedicationsSupplementsScreen
import com.joohnq.moodapp.view.screens.onboarding.MoodRateScreen
import com.joohnq.moodapp.view.screens.onboarding.PhysicalSymptomsScreen
import com.joohnq.moodapp.view.screens.onboarding.ProfessionalHelpScreen
import com.joohnq.moodapp.view.screens.onboarding.SleepQualityScreen
import com.joohnq.moodapp.view.screens.onboarding.StressRateScreen

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController,
) {
    navigation<OnboardingGraph>(startDestination = OnboardingGraph.MoodRateScreen) {
        composable<OnboardingGraph.MoodRateScreen> {
            MoodRateScreen(navigation = navController)
        }
        composable<OnboardingGraph.ProfessionalHelpScreen> {
            ProfessionalHelpScreen(
                navigation = navController,
            )
        }
        composable<OnboardingGraph.PhysicalSymptomsScreen> {
            PhysicalSymptomsScreen(
                navigation = navController,
            )
        }
        composable<OnboardingGraph.SleepQualityScreen> {
            SleepQualityScreen(
                navigation = navController,
            )
        }
        composable<OnboardingGraph.MedicationsSupplementsScreen> {
            MedicationsSupplementsScreen(
                navigation = navController,
            )
        }
        composable<OnboardingGraph.StressRateScreen> {
            StressRateScreen(
                navigation = navController,
            )
        }
        composable<OnboardingGraph.ExpressionAnalysisScreen> {
            ExpressionAnalysisScreen(
                navigation = navController,
            )
        }
    }
}