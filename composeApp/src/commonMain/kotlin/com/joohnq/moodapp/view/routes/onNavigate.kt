package com.joohnq.moodapp.view.routes

import androidx.navigation.NavController
import com.joohnq.moodapp.view.home.HomeScreenObject
import com.joohnq.moodapp.view.onboarding.ExpressionAnalysisScreenObject
import com.joohnq.moodapp.view.onboarding.GetUserNameScreenObject
import com.joohnq.moodapp.view.onboarding.MedicationsSupplementsScreenObject
import com.joohnq.moodapp.view.onboarding.MoodRateScreenObject
import com.joohnq.moodapp.view.onboarding.OnboardingScreenObject
import com.joohnq.moodapp.view.onboarding.PhysicalSymptomsScreenObject
import com.joohnq.moodapp.view.onboarding.ProfessionalHelpScreenObject
import com.joohnq.moodapp.view.onboarding.SleepQualityScreenObject
import com.joohnq.moodapp.view.onboarding.StressRateScreenObject
import com.joohnq.moodapp.view.welcome.WelcomeScreenObject

fun NavController.onNavigateToHomeScreen() {
    navigate(HomeScreenObject)
}

fun NavController.onNavigateToWelcomeScreen() {
    navigate(WelcomeScreenObject)
}

fun NavController.onNavigateToOnboardingScreen() {
    navigate(OnboardingScreenObject)
}

fun NavController.onNavigateToGetUserNameScreen() {
    navigate(GetUserNameScreenObject)
}

fun NavController.onNavigateToProfessionalHelp() {
    navigate(ProfessionalHelpScreenObject)
}

fun NavController.onNavigateToPhysicalSymptoms() {
    navigate(PhysicalSymptomsScreenObject)
}

fun NavController.onNavigateToSleepQuality() {
    navigate(SleepQualityScreenObject)
}

fun NavController.onNavigateToMedicationsSupplements() {
    navigate(MedicationsSupplementsScreenObject)
}

fun NavController.onNavigateToStressRate() {
    navigate(StressRateScreenObject)
}

fun NavController.onNavigateToExpressionAnalysis() {
    navigate(ExpressionAnalysisScreenObject)
}

fun NavController.onNavigateToGetUserName() {
    navigate(GetUserNameScreenObject)
}