package com.joohnq.onboarding.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.stress_level.domain.entity.StressLevel
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

    private fun updateSleepQuality(sleepQuality: SleepQuality) {
        _state.update { it.copy(sleepQuality = sleepQuality) }
    }

    private fun updateStressLevel(stressLevel: StressLevel) {
        _state.update { it.copy(stressLevel = stressLevel) }
    }

    private fun updateMood(mood: Mood) {
        _state.update { it.copy(statsRecord = it.statsRecord.copy(mood = mood)) }
    }

    private fun updateUserMedicationsSupplements(
        medicationsSupplements: MedicationsSupplements?,
    ) {
        _state.update { it.copy(medicationsSupplements = medicationsSupplements) }
    }

    private fun updateUserPhysicalSymptoms(physicalSymptoms: PhysicalSymptoms?) {
        _state.update { it.copy(physicalSymptoms = physicalSymptoms) }
    }

    private fun updateUserSoughtHelp(soughtHelp: ProfessionalHelp?) {
        _state.update { it.copy(soughtHelp = soughtHelp) }
    }

    private fun updateStatsRecordDescription(description: String) {
        _state.update { it.copy(statsRecord = it.statsRecord.copy(description = description)) }
    }

    private fun resetStatsRecord() {
        _state.update { it.copy(statsRecord = StatsRecord.init()) }
    }

    private fun setOnboardingStateForTesting(onboardingViewModelState: OnboardingViewModelState) {
        _state.update { onboardingViewModelState }
    }
}