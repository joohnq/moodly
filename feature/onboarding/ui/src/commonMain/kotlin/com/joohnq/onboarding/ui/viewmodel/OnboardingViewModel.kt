package com.joohnq.onboarding.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.ui.MoodResource
import com.joohnq.mood.ui.MoodResource.Companion.toDomain
import com.joohnq.sleep_quality.ui.SleepQualityResource
import com.joohnq.stress_level.ui.StressLevelResource
import com.joohnq.user.ui.MedicationsSupplementsResource
import com.joohnq.user.ui.PhysicalSymptomsResource
import com.joohnq.user.ui.ProfessionalHelpResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OnboardingViewModel : ViewModel() {
    private val _state = MutableStateFlow(OnboardingViewModelState())
    val state: StateFlow<OnboardingViewModelState> = _state.asStateFlow()

    fun onAction(intent: OnboardingViewModelIntent) {
        when (intent) {
            is OnboardingViewModelIntent.ResetStatsRecord -> resetStatsRecord()
            is OnboardingViewModelIntent.UpdateMood -> updateMood(intent.mood)
            is OnboardingViewModelIntent.UpdateSleepQuality -> updateSleepQuality(intent.sleepQuality)
            is OnboardingViewModelIntent.UpdateStatsRecordDescription ->
                updateStatsRecordDescription(intent.description)

            is OnboardingViewModelIntent.UpdateStressLevel -> updateStressLevel(intent.stressLevel)
            is OnboardingViewModelIntent.UpdateUserMedicationsSupplements -> updateUserMedicationsSupplements(
                intent.medicationsSupplements
            )

            is OnboardingViewModelIntent.UpdateUserPhysicalSymptoms ->
                updateUserPhysicalSymptoms(intent.physicalSymptoms)

            is OnboardingViewModelIntent.UpdateUserSoughtHelp -> updateUserSoughtHelp(intent.soughtHelp)
            is OnboardingViewModelIntent.UpdateSliderValue -> updateSliderValue(intent.sliderValue)
            is OnboardingViewModelIntent.SetOnboardingViewModelStateForTesting -> setOnboardingStateForTesting(
                intent.onboardingViewModelState
            )
        }
    }

    private fun updateSliderValue(sliderValue: Float) {
        _state.update { it.copy(sliderValue = sliderValue) }
    }

    private fun updateSleepQuality(sleepQuality: SleepQualityResource) {
        _state.update { it.copy(sleepQuality = sleepQuality) }
    }

    private fun updateStressLevel(stressLevel: StressLevelResource) {
        _state.update { it.copy(stressLevel = stressLevel) }
    }

    private fun updateMood(mood: MoodResource) {
        _state.update { it.copy(statsRecord = it.statsRecord.copy(mood = mood.toDomain())) }
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

    private fun updateStatsRecordDescription(description: String) {
        _state.update { it.copy(statsRecord = it.statsRecord.copy(description = description)) }
    }

    private fun resetStatsRecord() {
        _state.update { it.copy(statsRecord = StatsRecord()) }
    }

    private fun setOnboardingStateForTesting(onboardingViewModelState: OnboardingViewModelState) {
        _state.update { onboardingViewModelState }
    }
}