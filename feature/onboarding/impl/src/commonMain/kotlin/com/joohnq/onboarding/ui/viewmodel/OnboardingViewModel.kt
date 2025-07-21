package com.joohnq.onboarding.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.user.ui.resource.MedicationsSupplementsResource
import com.joohnq.user.ui.resource.PhysicalSymptomsResource
import com.joohnq.user.ui.resource.ProfessionalHelpResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel : ViewModel() {
    private val _state = MutableStateFlow(OnboardingState())
    val state: StateFlow<OnboardingState> = _state.asStateFlow()

    fun onAction(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.UpdateMood -> updateMood(intent.mood)
            is OnboardingIntent.UpdateSleepQuality -> updateSleepQuality(intent.sleepQuality)
            is OnboardingIntent.UpdateMoodRecordDescription ->
                updateMoodRecordDescription(intent.description)

            is OnboardingIntent.UpdateStressLevel -> updateStressLevel(intent.stressLevel)
            is OnboardingIntent.UpdateUserMedicationsSupplements -> updateUserMedicationsSupplements(
                intent.medicationsSupplements
            )

            is OnboardingIntent.UpdateUserPhysicalSymptoms ->
                updateUserPhysicalSymptoms(intent.physicalSymptoms)

            is OnboardingIntent.UpdateUserSoughtHelp -> updateUserSoughtHelp(intent.soughtHelp)
            is OnboardingIntent.UpdateSliderValue -> updateSliderValue(intent.sliderValue)
            is OnboardingIntent.SetOnboardingStateForTesting -> setOnboardingStateForTesting(
                intent.onboardingState
            )
        }
    }

    private fun updateSliderValue(sliderValue: Float) {
        _state.update { it.copy(sliderValue = sliderValue) }
    }

    private fun updateSleepQuality(sleepQuality: SleepQualityResource) {
        _state.update { it.copy(sleepQuality = it.sleepQuality.copy(sleepQuality = sleepQuality)) }
    }

    private fun updateStressLevel(stressLevel: StressLevelResource) {
        _state.update { it.copy(stressLevel = it.stressLevel.copy(stressLevel = stressLevel)) }
    }

    private fun updateMood(mood: MoodResource) {
        _state.update { it.copy(moodRecord = it.moodRecord.copy(mood = mood)) }
    }

    private fun updateUserMedicationsSupplements(
        medicationsSupplements: MedicationsSupplementsResource?,
    ) {
        _state.update { it.copy(medicationsSupplements = medicationsSupplements) }
    }

    private fun updateUserPhysicalSymptoms(physicalSymptoms: PhysicalSymptomsResource?) {
        _state.update { it.copy(physicalSymptoms = physicalSymptoms) }
    }

    private fun updateUserSoughtHelp(soughtHelp: ProfessionalHelpResource?) {
        _state.update { it.copy(soughtHelp = soughtHelp) }
    }

    private fun updateMoodRecordDescription(description: String) {
        _state.update { it.copy(moodRecord = it.moodRecord.copy(description = description)) }
    }

    private fun setOnboardingStateForTesting(onboardingState: OnboardingState) {
        _state.update { onboardingState }
    }
}