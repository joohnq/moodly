package com.joohnq.moodapp.view.screens

import com.joohnq.moodapp.entities.StatsRecord
import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    data object OnboardingGraph : Screens() {
        @Serializable data object ExpressionAnalysisScreen : Screens()
        @Serializable data object MedicationsSupplementsScreen : Screens()
        @Serializable data object MoodRateScreen : Screens()
        @Serializable data object PhysicalSymptomsScreen : Screens()
        @Serializable data object ProfessionalHelpScreen : Screens()
        @Serializable data object SleepQualityScreen : Screens()
        @Serializable data object StressLevelScreen : Screens()
    }

    @Serializable data object LoadingScreen : Screens()
    @Serializable data object WelcomeScreen : Screens()
    @Serializable data object GetUserNameScreen : Screens()

    @Serializable
    data object HomeGraph : Screens() {
        @Serializable data object HomeScreen : Screens()
        @Serializable data object JournalingScreen : Screens()
    }

    @Serializable data class MoodScreen(val statsRecord: StatsRecord) : Screens()
    @Serializable data object HealthJournalScreen : Screens()
    @Serializable data object AddMoodScreen : Screens()
    @Serializable data object SleepQualityScreen : Screens()
    @Serializable data object StressLevelScreen : Screens()
    @Serializable data object FreudScoreScreen : Screens()
    @Serializable data object ExpressionAnalysisScreen : Screens()
    @Serializable data object AddStressLevelScreen : Screens()
    @Serializable data object StressStressorsScreen : Screens()
    @Serializable data object AddSleepQualityScreen : Screens()
}

