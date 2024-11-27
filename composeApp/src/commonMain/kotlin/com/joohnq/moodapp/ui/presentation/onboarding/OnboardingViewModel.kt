package com.joohnq.moodapp.ui.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.domain.MedicationsSupplements
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.PhysicalSymptoms
import com.joohnq.moodapp.domain.ProfessionalHelp
import com.joohnq.moodapp.domain.SleepQuality
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.domain.StressLevel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class OnboardingState(
    val physicalSymptoms: PhysicalSymptoms? = null,
    val soughtHelp: ProfessionalHelp? = null,
    val medicationsSupplements: MedicationsSupplements? = null,
    val statsRecord: StatsRecord = StatsRecord.init(),
    val sleepQuality: SleepQuality = SleepQuality.Worst,
    val stressLevel: StressLevel = StressLevel.Three,
    val sliderValue: Float = 0f
)

sealed class OnboardingIntent {
    data class UpdateSleepQuality(val sleepQuality: SleepQuality) : OnboardingIntent()
    data class UpdateStressLevel(val stressLevel: StressLevel) : OnboardingIntent()
    data class UpdateMood(val mood: Mood) : OnboardingIntent()
    data class UpdateUserMedicationsSupplements(val medicationsSupplements: MedicationsSupplements?) :
        OnboardingIntent()

    data class UpdateUserPhysicalSymptoms(val physicalSymptoms: PhysicalSymptoms?) :
        OnboardingIntent()

    data class UpdateUserSoughtHelp(val soughtHelp: ProfessionalHelp?) : OnboardingIntent()
    data class UpdateStatsRecordDescription(val description: String) : OnboardingIntent()
    data class UpdateSliderValue(val sliderValue: Float) : OnboardingIntent()
    data object ResetStatsRecord : OnboardingIntent()
    data class SetOnboardingStateForTesting(val onboardingState: OnboardingState) :
        OnboardingIntent()
}

class OnboardingViewModel : ViewModel() {
    private val _onboardingState = MutableStateFlow(OnboardingState())
    val onboardingState: StateFlow<OnboardingState> = _onboardingState.asStateFlow()

    fun onAction(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.ResetStatsRecord -> resetStatsRecord()
            is OnboardingIntent.UpdateMood -> updateMood(intent.mood)
            is OnboardingIntent.UpdateSleepQuality -> updateSleepQuality(intent.sleepQuality)
            is OnboardingIntent.UpdateStatsRecordDescription -> updateStatsRecordDescription(intent.description)
            is OnboardingIntent.UpdateStressLevel -> updateStressLevel(intent.stressLevel)
            is OnboardingIntent.UpdateUserMedicationsSupplements -> updateUserMedicationsSupplements(
                intent.medicationsSupplements
            )

            is OnboardingIntent.UpdateUserPhysicalSymptoms -> updateUserPhysicalSymptoms(intent.physicalSymptoms)
            is OnboardingIntent.UpdateUserSoughtHelp -> updateUserSoughtHelp(intent.soughtHelp)
            is OnboardingIntent.UpdateSliderValue -> updateSliderValue(intent.sliderValue)
            is OnboardingIntent.SetOnboardingStateForTesting -> setOnboardingStateForTesting(intent.onboardingState)
        }
    }

    private fun updateSliderValue(sliderValue: Float) {
        _onboardingState.update { it.copy(sliderValue = sliderValue) }
    }

    private fun updateSleepQuality(sleepQuality: SleepQuality) {
        _onboardingState.update { it.copy(sleepQuality = sleepQuality) }
    }

    private fun updateStressLevel(stressLevel: StressLevel) {
        _onboardingState.update { it.copy(stressLevel = stressLevel) }
    }

    private fun updateMood(mood: Mood) {
        _onboardingState.update { it.copy(statsRecord = it.statsRecord.copy(mood = mood)) }
    }

    private fun updateUserMedicationsSupplements(
        medicationsSupplements: MedicationsSupplements?,
    ) {
        _onboardingState.update { it.copy(medicationsSupplements = medicationsSupplements) }
    }

    private fun updateUserPhysicalSymptoms(physicalSymptoms: PhysicalSymptoms?) {
        _onboardingState.update { it.copy(physicalSymptoms = physicalSymptoms) }
    }

    private fun updateUserSoughtHelp(soughtHelp: ProfessionalHelp?) {
        _onboardingState.update { it.copy(soughtHelp = soughtHelp) }
    }

    private fun updateStatsRecordDescription(description: String) {
        _onboardingState.update { it.copy(statsRecord = it.statsRecord.copy(description = description)) }
    }

    private fun resetStatsRecord() {
        _onboardingState.update { it.copy(statsRecord = StatsRecord.init()) }
    }

    private fun setOnboardingStateForTesting(onboardingState: OnboardingState) {
        _onboardingState.update { onboardingState }
    }
}