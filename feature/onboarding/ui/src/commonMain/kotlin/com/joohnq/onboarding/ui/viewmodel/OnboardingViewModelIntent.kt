package com.joohnq.onboarding.ui.viewmodel

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.Mood
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.stress_level.domain.entity.StressLevel

sealed class OnboardingViewModelIntent {
    data class UpdateSleepQuality(val sleepQuality: SleepQuality) : OnboardingViewModelIntent()
    data class UpdateStressLevel(val stressLevel: StressLevel) : OnboardingViewModelIntent()
    data class UpdateMood(val mood: Mood) : OnboardingViewModelIntent()
    data class UpdateUserMedicationsSupplements(val medicationsSupplements: MedicationsSupplements?) :
        OnboardingViewModelIntent()

    data class UpdateUserPhysicalSymptoms(val physicalSymptoms: PhysicalSymptoms?) :
        OnboardingViewModelIntent()

    data class UpdateUserSoughtHelp(val soughtHelp: ProfessionalHelp?) : OnboardingViewModelIntent()
    data class UpdateStatsRecordDescription(val description: String) : OnboardingViewModelIntent()
    data class UpdateSliderValue(val sliderValue: Float) : OnboardingViewModelIntent()
    data object ResetStatsRecord : OnboardingViewModelIntent()
    data class SetOnboardingViewModelStateForTesting(val onboardingViewModelState: OnboardingViewModelState) :
        OnboardingViewModelIntent()
}