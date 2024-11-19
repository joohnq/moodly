package com.joohnq.moodapp.view.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.view.screens.Screens

fun NavController.onNavigateToHomeGraph() {
    navigate(Screens.HomeGraph) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToWelcomeScreen() {
    navigate(Screens.WelcomeScreen) {
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

fun NavController.onNavigateToProfessionalHelp() {
    navigate(Screens.OnboardingGraph.ProfessionalHelpScreen)
}

fun NavController.onNavigateToPhysicalSymptoms() {
    navigate(Screens.OnboardingGraph.PhysicalSymptomsScreen)
}

fun NavController.onNavigateToOnboardingSleepQuality() {
    navigate(Screens.OnboardingGraph.SleepQualityScreen)
}

fun NavController.onNavigateToMedicationsSupplements() {
    navigate(Screens.OnboardingGraph.MedicationsSupplementsScreen)
}

fun NavController.onNavigateToOnboardingStressLevel() {
    navigate(Screens.OnboardingGraph.StressLevelScreen)
}

fun NavController.onNavigateToSleepQuality() {
    navigate(Screens.SleepQualityScreen)
}

fun NavController.onNavigateToStressLevel() {
    navigate(Screens.StressLevelScreen)
}

fun NavController.onNavigateToOnboardingExpressionAnalysis() {
    navigate(Screens.OnboardingGraph.ExpressionAnalysisScreen)
}

fun NavController.onNavigateToExpressionAnalysis() {
    navigate(Screens.ExpressionAnalysisScreen)
}

fun NavController.onNavigateToFreudScore() {
    navigate(Screens.FreudScoreScreen)
}

fun NavController.onNavigateToMood(statsRecord: StatsRecord) {
    navigate(Screens.MoodScreen(statsRecord))
}

fun NavController.onNavigateToMood() {
    navigate(Screens.MoodScreen)
}

fun NavController.onNavigateToHealthJournal() {
    navigate(Screens.HealthJournalScreen)
}

fun NavController.onNavigateToAddStressLevel() {
    navigate(Screens.AddStressLevelScreen)
}

fun NavController.onNavigateToStressStressors() {
    navigate(Screens.StressStressorsScreen)
}

fun NavController.onNavigateToAddSleepQuality() {
    navigate(Screens.AddSleepQualityScreen)
}

fun NavController.onNavigateToAddJournalingScreen() {
    navigate(Screens.AddJournalingScreen)
}

fun NavController.onNavigateToAddMood() {
    navigate(Screens.AddMoodScreen) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.onNavigateToRoute(route: Screens) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
