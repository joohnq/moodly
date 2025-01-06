package com.joohnq.onboarding.ui.viewmodel

import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.user.ui.resource.MedicationsSupplementsResource
import com.joohnq.user.ui.resource.PhysicalSymptomsResource
import com.joohnq.user.ui.resource.ProfessionalHelpResource

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