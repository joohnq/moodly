package com.joohnq.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object Loading : Destination

    @Serializable
    data object Welcome : Destination

    @Serializable
    object Onboarding {
        @Serializable
        data object MoodRate : Destination

        @Serializable
        data object ProfessionalHelp : Destination

        @Serializable
        data object PhysicalSymptoms : Destination

        @Serializable
        data object SleepQuality : Destination

        @Serializable
        data object MedicationsSupplements : Destination

        @Serializable
        data object StressLevel : Destination

        @Serializable
        data object ExpressionAnalysis : Destination
    }

    @Serializable
    data object Auth : Destination {
        @Serializable
        data object Avatar : Destination

        @Serializable
        data object UserName : Destination
    }

    @Serializable
    data object Security : Destination {
        @Serializable
        data object Security : Destination

        @Serializable
        data object SecurityConfirmed : Destination

        @Serializable
        data object PIN : Destination

        @Serializable
        data object UnLock : Destination
    }

    @Serializable
    data object App : Destination {
        @Serializable
        data object DashBoard : Destination {
            @Serializable
            data object Home : Destination
        }

        @Serializable
        data object FreudScore : Destination

        @Serializable
        data object StressLevelOverview : Destination

        @Serializable
        data object StressLevelHistory : Destination

        @Serializable
        data object AddStressLevel : Destination

        @Serializable
        data object StressStressors : Destination

        @Serializable
        data object MoodOverview : Destination

        @Serializable
        data object MoodHistory : Destination

        @Serializable
        data object ExpressionAnalysis : Destination

        @Serializable
        data object AddMood : Destination

        @Serializable
        data object SelfJournalOverview : Destination

        @Serializable
        data object SleepQualityOverview : Destination

        @Serializable
        data object SleepQualityHistory : Destination

        @Serializable
        data object AddSleepQuality : Destination

        @Serializable
        data object AddSelfJournal : Destination

        @Serializable
        data object SelfJournalHistory : Destination

        @Serializable
        data class EditSelfJournal(
            val id: Int,
        ) : Destination
    }
}
