package com.joohnq.onboarding.impl.ui.viewmodel

import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelResource
import com.joohnq.ui.UnidirectionalViewModel
import com.joohnq.user.impl.ui.resource.MedicationsSupplementsResource
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource
import com.joohnq.user.impl.ui.resource.ProfessionalHelpResource

sealed interface OnboardingContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>
    sealed interface Intent {
        data class UpdateSleepQuality(val sleepQuality: SleepQualityResource) : Intent
        data class UpdateStressLevel(val stressLevel: StressLevelResource) : Intent
        data class UpdateMood(val mood: MoodResource) : Intent
        data class UpdateUserMedicationsSupplements(val medicationsSupplements: MedicationsSupplementsResource?) : Intent
        data class UpdateUserPhysicalSymptoms(val physicalSymptoms: PhysicalSymptomsResource?) : Intent
        data class UpdateUserSoughtHelp(val soughtHelp: ProfessionalHelpResource?) : Intent
        data class UpdateMoodRecordDescription(val description: String) : Intent
        data class UpdateSliderValue(val sliderValue: Float) : Intent
    }

    sealed interface SideEffect

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
        val sliderValue: Float = 0f
    )
}