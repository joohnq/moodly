package com.joohnq.moodapp.view.routes

import androidx.navigation.NavController
import com.joohnq.moodapp.view.Screens

fun NavController.onNavigateToMoodRateScreen() {
    navigate(Screens.OnboardingScreenGraph.MoodRateScreen)
}

fun NavController.onNavigateToHomeScreen() {
    navigate(Screens.HomeScreen)
}

fun NavController.onNavigateToWelcomeScreen() {
    navigate(Screens.WelcomeScreen)
}

fun NavController.onNavigateToOnboardingScreen() {
    navigate(Screens.OnboardingScreenGraph)
}

fun NavController.onNavigateToGetUserNameScreen() {
    navigate(Screens.OnboardingScreenGraph.GetUserNameScreen)
}

fun NavController.onNavigateToProfessionalHelp() {
    navigate(Screens.OnboardingScreenGraph.ProfessionalHelpScreen)
}

fun NavController.onNavigateToPhysicalSymptoms() {
    navigate(Screens.OnboardingScreenGraph.PhysicalSymptomsScreen)
}

fun NavController.onNavigateToSleepQuality() {
    navigate(Screens.OnboardingScreenGraph.SleepQualityScreen)
}

fun NavController.onNavigateToMedicationsSupplements() {
    navigate(Screens.OnboardingScreenGraph.MedicationsSupplementsScreen)
}

fun NavController.onNavigateToStressRate() {
    navigate(Screens.OnboardingScreenGraph.StressRateScreen)
}

fun NavController.onNavigateToExpressionAnalysis() {
    navigate(Screens.OnboardingScreenGraph.ExpressionAnalysisScreen)
}
