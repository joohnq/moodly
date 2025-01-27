package com.joohnq.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable data object Loading : Destination
    @Serializable data object Welcome : Destination

    @Serializable
    object Onboarding {
        @Serializable data object MoodRate : Destination
        @Serializable data object ProfessionalHelp : Destination
        @Serializable data object PhysicalSymptoms : Destination
        @Serializable data object SleepQuality : Destination
        @Serializable data object MedicationsSupplements : Destination
        @Serializable data object StressLevel : Destination
        @Serializable data object ExpressionAnalysis : Destination
    }

    @Serializable data object Auth : Destination {
        @Serializable data object Avatar : Destination
        @Serializable data object UserName : Destination
    }

    @Serializable data object Security : Destination {
        @Serializable data object Security : Destination
        @Serializable data object SecurityConfirmed : Destination
        @Serializable data object PIN : Destination
        @Serializable data object UnLock : Destination
        @Serializable data object CorruptedSecurity : Destination
    }

    @Serializable data object App : Destination {
        @Serializable data object DashBoard : Destination {
            @Serializable data object Home : Destination
            @Serializable data object Journaling : Destination
        }

        @Serializable data object FreudScore : Destination
        @Serializable data object StressLevel : Destination
        @Serializable data object AddStressLevel : Destination
        @Serializable data object StressStressors : Destination
        @Serializable data class Mood(val id: Int? = null) : Destination
        @Serializable data object ExpressionAnalysis : Destination
        @Serializable data object AddStat : Destination
        @Serializable data object HealthJournal : Destination
        @Serializable data object MindfulJournal : Destination
        @Serializable data class SleepQuality(val id: Int) : Destination
        @Serializable data object SleepHistory : Destination
        @Serializable data object AddSleepQuality : Destination
        @Serializable data object AddJournaling : Destination
        @Serializable data class AllJournals(val localDate: String? = null) : Destination
        @Serializable data class EditJournaling(val id: Int) : Destination
    }
}