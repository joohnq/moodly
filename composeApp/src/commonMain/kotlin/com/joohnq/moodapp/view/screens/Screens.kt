package com.joohnq.moodapp.view.screens

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

    @Serializable data class MoodScreen(val id: Int? = null) : Screens()
    @Serializable data object HealthJournalScreen : Screens()
    @Serializable data object AddMoodScreen : Screens()
    @Serializable data object SleepQualityScreen : Screens()
    @Serializable data object StressLevelScreen : Screens()
    @Serializable data object FreudScoreScreen : Screens()
    @Serializable data object ExpressionAnalysisScreen : Screens()
    @Serializable data object AddStressLevelScreen : Screens()
    @Serializable data object StressStressorsScreen : Screens()
    @Serializable data object AddSleepQualityScreen : Screens()
    @Serializable data object AddJournalingScreen : Screens()
    @Serializable data class EditJournalingScreen(val id: Int) : Screens()

    companion object {
        fun getAll(): List<Screens> =
            listOf(
                OnboardingGraph.ExpressionAnalysisScreen,
                OnboardingGraph.MedicationsSupplementsScreen,
                OnboardingGraph.MoodRateScreen,
                OnboardingGraph.PhysicalSymptomsScreen,
                OnboardingGraph.ProfessionalHelpScreen,
                OnboardingGraph.SleepQualityScreen,
                OnboardingGraph.StressLevelScreen,
                LoadingScreen,
                WelcomeScreen,
                GetUserNameScreen,
                HomeGraph.HomeScreen,
                HomeGraph.JournalingScreen,
                MoodScreen(null),
                HealthJournalScreen,
                AddMoodScreen,
                SleepQualityScreen,
                StressLevelScreen,
                FreudScoreScreen,
                ExpressionAnalysisScreen,
                AddStressLevelScreen,
                StressStressorsScreen,
                AddSleepQualityScreen,
                EditJournalingScreen(-1),
            )
    }
}

fun String?.toRoute(): Screens {
    val list = Screens.getAll()
    return list.find { it::class.qualifiedName == this } ?: throw IllegalArgumentException()
}
