package com.joohnq.navigation

import kotlinx.serialization.Serializable

sealed class Destination {
    @Serializable data object Loading : Destination()
    @Serializable data object Welcome : Destination()

    @Serializable
    object Onboarding {
        @Serializable data object MoodRate : Destination()
        @Serializable data object ProfessionalHelp : Destination()
        @Serializable data object PhysicalSymptoms : Destination()
        @Serializable data object SleepQuality : Destination()
        @Serializable data object MedicationsSupplements : Destination()
        @Serializable data object StressLevel : Destination()
        @Serializable data object ExpressionAnalysis : Destination()
    }

    @Serializable data object Auth : Destination()
    @Serializable data object Dashboard : Destination() {
        @Serializable data object Home : Destination()
        @Serializable data object Journaling : Destination()
    }

    @Serializable data object Mood : Destination() {
        @Serializable data object Mood : Destination()
        @Serializable data object ExpressionAnalysis : Destination()
        @Serializable data object AddStat : Destination()
    }
}