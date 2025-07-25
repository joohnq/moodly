package com.joohnq.onboarding.impl.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelResource
import com.joohnq.user.impl.ui.resource.MedicationsSupplementsResource
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource
import com.joohnq.user.impl.ui.resource.ProfessionalHelpResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel : ViewModel() {
    private val _state = MutableStateFlow(OnboardingContract.State())
    val state: StateFlow<OnboardingContract.State> = _state.asStateFlow()

    fun onAction(intent: OnboardingContract.Intent) {
        when (intent) {
            is OnboardingContract.Intent.UpdateMood -> updateMood(intent.mood)
            is OnboardingContract.Intent.UpdateSleepQuality -> updateSleepQuality(intent.sleepQuality)
            is OnboardingContract.Intent.UpdateMoodRecordDescription ->
                updateMoodRecordDescription(intent.description)

            is OnboardingContract.Intent.UpdateStressLevel -> updateStressLevel(intent.stressLevel)
            is OnboardingContract.Intent.UpdateUserMedicationsSupplements -> updateUserMedicationsSupplements(
                intent.medicationsSupplements
            )

            is OnboardingContract.Intent.UpdateUserPhysicalSymptoms ->
                updateUserPhysicalSymptoms(intent.physicalSymptoms)

            is OnboardingContract.Intent.UpdateUserSoughtHelp -> updateUserSoughtHelp(intent.soughtHelp)
            is OnboardingContract.Intent.UpdateSliderValue -> updateSliderValue(intent.sliderValue)
            is OnboardingContract.Intent.SetOnboardingStateForTesting -> setOnboardingStateForTesting(
                intent.state
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

    private fun setOnboardingStateForTesting(state: OnboardingContract.State) {
        _state.update { state }
    }
}