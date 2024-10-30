package com.joohnq.moodapp.view.routes

import androidx.navigation.NavController
import com.joohnq.moodapp.view.Screens

fun NavController.onNavigateToHomeGraph() {
    navigate(Screens.HomeGraph) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToWelcomeScreen() {
    navigate(Screens.WelcomeScreen){
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToOnboardingScreen() {
    navigate(Screens.OnboardingGraph) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToGetUserNameScreen() {
    navigate(Screens.GetUserNameScreen) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToCompilingData() {
    navigate(Screens.CompilingDataScreen){
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToProfessionalHelp() {
    navigate(Screens.OnboardingGraph.ProfessionalHelpScreen)
}

fun NavController.onNavigateToPhysicalSymptoms() {
    navigate(Screens.OnboardingGraph.PhysicalSymptomsScreen)
}

fun NavController.onNavigateToSleepQuality() {
    navigate(Screens.OnboardingGraph.SleepQualityScreen)
}

fun NavController.onNavigateToMedicationsSupplements() {
    navigate(Screens.OnboardingGraph.MedicationsSupplementsScreen)
}

fun NavController.onNavigateToStressRate() {
    navigate(Screens.OnboardingGraph.StressRateScreen)
}

fun NavController.onNavigateToExpressionAnalysis() {
    navigate(Screens.OnboardingGraph.ExpressionAnalysisScreen)
}
