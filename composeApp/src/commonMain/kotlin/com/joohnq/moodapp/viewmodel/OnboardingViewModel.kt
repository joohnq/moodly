package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.entities.MedicationsSupplements
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.PhysicalSymptoms
import com.joohnq.moodapp.entities.ProfessionalHelp
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.entities.StressLevel
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
)

class OnboardingViewModel : ViewModel() {
    private val _onboardingState = MutableStateFlow(OnboardingState())
    val onboardingState: StateFlow<OnboardingState> = _onboardingState.asStateFlow()

    fun updateSleepQuality(sleepQuality: SleepQuality) {
        _onboardingState.update { it.copy(sleepQuality = sleepQuality) }
    }

    fun updateStressLevel(stressLevel: StressLevel) {
        _onboardingState.update { it.copy(stressLevel = stressLevel) }
    }

    fun updateMood(mood: Mood) {
        _onboardingState.update { it.copy(statsRecord = it.statsRecord.copy(mood = mood)) }
    }

    fun updateUserMedicationsSupplements(
        medicationsSupplements: MedicationsSupplements,
    ) {
        _onboardingState.update { it.copy(medicationsSupplements = medicationsSupplements) }
    }

    fun updateUserPhysicalSymptoms(physicalSymptoms: PhysicalSymptoms) {
        _onboardingState.update { it.copy(physicalSymptoms = physicalSymptoms) }
    }

    fun updateUserSoughtHelp(soughtHelp: ProfessionalHelp) {
        _onboardingState.update { it.copy(soughtHelp = soughtHelp) }
    }

    fun updateStatsRecordDescription(description: String) {
        _onboardingState.update { it.copy(statsRecord = it.statsRecord.copy(description = description)) }
    }

    fun resetStatsRecord() {
        _onboardingState.update { it.copy(statsRecord = StatsRecord.init()) }
    }

    fun setCurrentStatsRecord(statsRecord: StatsRecord) {
        _onboardingState.update { it.copy(statsRecord = statsRecord) }
    }
}