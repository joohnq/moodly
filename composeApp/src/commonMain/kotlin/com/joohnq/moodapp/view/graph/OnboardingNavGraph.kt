package com.joohnq.moodapp.view.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.screens.onboarding.OnboardingExpressionAnalysisScreen
import com.joohnq.moodapp.view.screens.onboarding.OnboardingMedicationsSupplementsScreen
import com.joohnq.moodapp.view.screens.onboarding.OnboardingMoodRateScreen
import com.joohnq.moodapp.view.screens.onboarding.OnboardingPhysicalSymptomsScreen
import com.joohnq.moodapp.view.screens.onboarding.OnboardingProfessionalHelpScreen
import com.joohnq.moodapp.view.screens.onboarding.OnboardingSleepQualityScreen
import com.joohnq.moodapp.view.screens.onboarding.OnboardingStressLevelScreen

fun NavGraphBuilder.onboardingNavGraph(
    navController: NavController,
) {
    navigation<Screens.OnboardingGraph>(startDestination = Screens.OnboardingGraph.MoodRateScreen) {
        composable<Screens.OnboardingGraph.MoodRateScreen> {
            OnboardingMoodRateScreen(navigation = navController)
        }
        composable<Screens.OnboardingGraph.ProfessionalHelpScreen> {
            OnboardingProfessionalHelpScreen(navigation = navController)
        }
        composable<Screens.OnboardingGraph.PhysicalSymptomsScreen> {
            OnboardingPhysicalSymptomsScreen(navigation = navController)
        }
        composable<Screens.OnboardingGraph.SleepQualityScreen> {
            OnboardingSleepQualityScreen(navigation = navController)
        }
        composable<Screens.OnboardingGraph.MedicationsSupplementsScreen> {
            OnboardingMedicationsSupplementsScreen(navigation = navController)
        }
        composable<Screens.OnboardingGraph.StressLevelScreen> {
            OnboardingStressLevelScreen(navigation = navController)
        }
        composable<Screens.OnboardingGraph.ExpressionAnalysisScreen> {
            OnboardingExpressionAnalysisScreen(navigation = navController)
        }
    }
}