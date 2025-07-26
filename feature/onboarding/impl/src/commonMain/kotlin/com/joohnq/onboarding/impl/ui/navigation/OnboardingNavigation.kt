package com.joohnq.onboarding.impl.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.onboarding.impl.ui.presentation.onboarding_expression_analysis.OnboardingExpressionAnalysisScreen
import com.joohnq.onboarding.impl.ui.presentation.onboarding_medications_supplements.OnboardingMedicationsSupplementsScreen
import com.joohnq.onboarding.impl.ui.presentation.onboarding_mood_rate.OnboardingMoodRateScreen
import com.joohnq.onboarding.impl.ui.presentation.onboarding_physical_symptoms.OnboardingPhysicalSymptomsScreen
import com.joohnq.onboarding.impl.ui.presentation.onboarding_professional_help.OnboardingProfessionalHelpScreen
import com.joohnq.onboarding.impl.ui.presentation.onboarding_sleep_quality.OnboardingSleepQualityScreen
import com.joohnq.onboarding.impl.ui.presentation.onboarding_stress_level.OnboardingStressLevelScreen

fun NavGraphBuilder.onboardingNavigation(
    onNavigate: (Destination) -> Unit,
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
    onGoBack: () -> Unit,
) {
    navigation<NavigationGraph.Onboarding>(startDestination = Destination.Onboarding.MoodRate) {
        composable<Destination.Onboarding.MoodRate> {
            OnboardingMoodRateScreen(
                onNavigateToProfessionalHelp = {
                    onNavigate(Destination.Onboarding.ProfessionalHelp)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.ProfessionalHelp> {
            OnboardingProfessionalHelpScreen(
                onNavigateToPhysicalSymptoms = {
                    onNavigate(Destination.Onboarding.PhysicalSymptoms)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.PhysicalSymptoms> {
            OnboardingPhysicalSymptomsScreen(
                onNavigateToSleepQuality = {
                    onNavigate(Destination.Onboarding.SleepQuality)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.SleepQuality> {
            OnboardingSleepQualityScreen(
                onNavigateToMedicationsSupplements = {
                    onNavigate(Destination.Onboarding.MedicationsSupplements)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.MedicationsSupplements> {
            OnboardingMedicationsSupplementsScreen(
                onNavigateToStressLevel = {
                    onNavigate(Destination.Onboarding.StressLevel)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.Onboarding.StressLevel> {
            OnboardingStressLevelScreen(
                onNavigateToExpressionAnalysis = {
                    onNavigate(Destination.Onboarding.ExpressionAnalysis)
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