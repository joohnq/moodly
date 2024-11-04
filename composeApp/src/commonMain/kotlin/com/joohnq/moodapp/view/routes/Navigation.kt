package com.joohnq.moodapp.view.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.view.screens.AddMoodScreen
import com.joohnq.moodapp.view.screens.CompilingDataScreen
import com.joohnq.moodapp.view.screens.FreudScoreScreen
import com.joohnq.moodapp.view.screens.GetUserNameScreen
import com.joohnq.moodapp.view.screens.HealthJournalScreen
import com.joohnq.moodapp.view.screens.HomeGraph
import com.joohnq.moodapp.view.screens.MoodScreen
import com.joohnq.moodapp.view.screens.OnboardingGraph
import com.joohnq.moodapp.view.screens.WelcomeScreen

fun NavController.onNavigateToHomeGraph() {
    navigate(HomeGraph) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToWelcomeScreen() {
    navigate(WelcomeScreen) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToOnboardingScreen() {
    navigate(OnboardingGraph) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToGetUserNameScreen() {
    navigate(GetUserNameScreen) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToCompilingData() {
    navigate(CompilingDataScreen) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavController.onNavigateToProfessionalHelp() {
    navigate(OnboardingGraph.ProfessionalHelpScreen)
}

fun NavController.onNavigateToPhysicalSymptoms() {
    navigate(OnboardingGraph.PhysicalSymptomsScreen)
}

fun NavController.onNavigateToSleepQuality() {
    navigate(OnboardingGraph.SleepQualityScreen)
}

fun NavController.onNavigateToMedicationsSupplements() {
    navigate(OnboardingGraph.MedicationsSupplementsScreen)
}

fun NavController.onNavigateToStressRate() {
    navigate(OnboardingGraph.StressRateScreen)
}

fun NavController.onNavigateToExpressionAnalysis() {
    navigate(OnboardingGraph.ExpressionAnalysisScreen)
}

fun NavController.onNavigateToFreudScore() {
    navigate(FreudScoreScreen)
}

fun NavController.onNavigateToMood(statsRecord: StatsRecord) {
    navigate(MoodScreen(statsRecord))
}

fun NavController.onNavigateToHealthJournal() {
    navigate(HealthJournalScreen)
}

fun NavController.onNavigateToAddMood() {
    navigate(AddMoodScreen) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.onNavigateToRoute(route: Any) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
