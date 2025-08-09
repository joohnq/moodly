package com.joohnq.onboarding.impl.ui.viewmodel

import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.add.ui.resource.MoodResource
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
        data class ChangeSleepQuality(
            val sleepQuality: SleepQualityResource,
        ) : Intent

        data class ChangeStressLevel(
            val stressLevel: StressLevelResource,
        ) : Intent

        data class ChangeMood(
            val mood: MoodResource,
        ) : Intent

        data class ChangeMedicationsSupplements(
            val medicationsSupplements: MedicationsSupplementsResource?,
        ) : Intent

        data class ChangePhysicalSymptoms(
            val physicalSymptoms: PhysicalSymptomsResource?,
        ) : Intent

        data class ChangeProfessionalHelp(
            val professionalHelp: ProfessionalHelpResource?,
        ) : Intent

        data class ChangeMoodDescription(
            val description: String,
        ) : Intent

        data object AddItems : Intent

        data class ChangeSliderValue(
            val sliderValue: Float,
        ) : Intent
    }

    sealed interface SideEffect {
        data object NavigateNext : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val physicalSymptoms: PhysicalSymptomsResource? = null,
        val soughtHelp: ProfessionalHelpResource? = null,
        val medicationsSupplements: MedicationsSupplementsResource? = null,
        val moodRecord: MoodRecordResource = MoodRecordResource(),
        val sleepQuality: SleepQualityRecordResource =
            SleepQualityRecordResource(
                sleepQuality = SleepQualityResource.Worst
            ),
        val stressLevel: StressLevelRecordResource =
            StressLevelRecordResource(
                stressLevel = StressLevelResource.Three
            ),
        val sliderValue: Float = 0f,
        val isLoading: Boolean = false,
    )
}
