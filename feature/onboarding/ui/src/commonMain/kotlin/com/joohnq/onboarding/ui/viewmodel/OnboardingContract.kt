package com.joohnq.onboarding.ui.viewmodel

import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.user.ui.resource.MedicationsSupplementsResource
import com.joohnq.user.ui.resource.PhysicalSymptomsResource
import com.joohnq.user.ui.resource.ProfessionalHelpResource

sealed interface OnboardingContract {
    sealed interface Intent: OnboardingContract {
        data class UpdateSleepQuality(val sleepQuality: SleepQualityResource) :
            Intent

        data class UpdateStressLevel(val stressLevel: StressLevelResource) : Intent
        data class UpdateMood(val mood: MoodResource) : Intent
        data class UpdateUserMedicationsSupplements(val medicationsSupplements: MedicationsSupplementsResource?) :
            Intent

        data class UpdateUserPhysicalSymptoms(val physicalSymptoms: PhysicalSymptomsResource?) :
            Intent

        data class UpdateUserSoughtHelp(val soughtHelp: ProfessionalHelpResource?) :
            Intent

        data class UpdateMoodRecordDescription(val description: String) : Intent
        data class UpdateSliderValue(val sliderValue: Float) : Intent
        data class SetStateForTesting(val state: State) :
            Intent
    }

    data class State(
        val physicalSymptoms: PhysicalSymptomsResource? = null,
        val soughtHelp: ProfessionalHelpResource? = null,
        val medicationsSupplements: MedicationsSupplementsResource? = null,
        val moodRecord: MoodRecordResource = MoodRecordResource(),
        val sleepQuality: SleepQualityRecordResource = SleepQualityRecordResource(
            sleepQuality = SleepQualityResource.Worst
        ),
        val stressLevel: StressLevelRecordResource = StressLevelRecordResource(
            stressLevel = StressLevelResource.Three
        ),
        val sliderValue: Float = 0f,
    ): OnboardingContract

    sealed interface Event: OnboardingContract {
        data object GoBack : Event
        data object OnContinue : Event
    }
}