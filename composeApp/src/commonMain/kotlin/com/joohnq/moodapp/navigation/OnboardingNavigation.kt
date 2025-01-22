package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis.OnboardingExpressionAnalysisScreen
import com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements.OnboardingMedicationsSupplementsScreen
import com.joohnq.onboarding.ui.presentation.onboarding_mood_rate.OnboardingMoodRateScreen
import com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms.OnboardingPhysicalSymptomsScreen
import com.joohnq.onboarding.ui.presentation.onboarding_professional_help.OnboardingProfessionalHelpScreen
import com.joohnq.onboarding.ui.presentation.onboarding_sleep_quality.OnboardingSleepQualityScreen
import com.joohnq.onboarding.ui.presentation.onboarding_stress_level.OnboardingStressLevelScreen

fun NavGraphBuilder.onboardingNavigation(
    onNavigate: (Destination, Boolean) -> Unit,
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
    onGoBack: () -> Unit,
) {
    navigation<NavigationGraph.Onboarding>(startDestination = Destination.Onboarding.MoodRate) {
        composable<Destination.Onboarding.MoodRate> {
            OnboardingMoodRateScreen(
                onNavigateToProfessionalHelp = {
                    onNavigate(Destination.Onboarding.ProfessionalHelp, false)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.ProfessionalHelp> {
            OnboardingProfessionalHelpScreen(
                onNavigateToPhysicalSymptoms = {
                    onNavigate(Destination.Onboarding.PhysicalSymptoms, false)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.PhysicalSymptoms> {
            OnboardingPhysicalSymptomsScreen(
                onNavigateToSleepQuality = {
                    onNavigate(Destination.Onboarding.SleepQuality, false)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.SleepQuality> {
            OnboardingSleepQualityScreen(
                onNavigateToMedicationsSupplements = {
                    onNavigate(Destination.Onboarding.MedicationsSupplements, false)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.MedicationsSupplements> {
            OnboardingMedicationsSupplementsScreen(
                onNavigateToStressLevel = {
                    onNavigate(Destination.Onboarding.StressLevel, false)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.StressLevel> {
            OnboardingStressLevelScreen(
                onNavigateToExpressionAnalysis = {
                    onNavigate(Destination.Onboarding.ExpressionAnalysis, false)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.ExpressionAnalysis> {
            OnboardingExpressionAnalysisScreen(
                onNavigateToUserName = {
                    onNavigateGraph(NavigationGraph.Auth, true)
                },
                onGoBack = onGoBack
            )
        }
    }
}