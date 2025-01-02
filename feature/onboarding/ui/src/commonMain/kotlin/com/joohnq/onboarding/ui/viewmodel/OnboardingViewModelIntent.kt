package com.joohnq.onboarding.ui.viewmodel

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.ui.MoodResource
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.ui.SleepQualityResource
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.ui.StressLevelResource
import com.joohnq.user.ui.MedicationsSupplementsResource
import com.joohnq.user.ui.PhysicalSymptomsResource
import com.joohnq.user.ui.ProfessionalHelpResource

sealed class OnboardingViewModelIntent {
    data class UpdateSleepQuality(val sleepQuality: SleepQualityResource) :
        OnboardingViewModelIntent()

    data class UpdateStressLevel(val stressLevel: StressLevelResource) : OnboardingViewModelIntent()
    data class UpdateMood(val mood: MoodResource) : OnboardingViewModelIntent()
    data class UpdateUserMedicationsSupplements(val medicationsSupplements: MedicationsSupplementsResource?) :
        OnboardingViewModelIntent()

    data class UpdateUserPhysicalSymptoms(val physicalSymptoms: PhysicalSymptomsResource?) :
        OnboardingViewModelIntent()

    data class UpdateUserSoughtHelp(val soughtHelp: ProfessionalHelpResource?) :
        OnboardingViewModelIntent()

    data class UpdateStatsRecordDescription(val description: String) : OnboardingViewModelIntent()
    data class UpdateSliderValue(val sliderValue: Float) : OnboardingViewModelIntent()
    data object ResetStatsRecord : OnboardingViewModelIntent()
    data class SetOnboardingViewModelStateForTesting(val onboardingViewModelState: OnboardingViewModelState) :
        OnboardingViewModelIntent()
}