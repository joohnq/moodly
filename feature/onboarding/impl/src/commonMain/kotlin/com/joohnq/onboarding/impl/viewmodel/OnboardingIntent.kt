package com.joohnq.onboarding.impl.viewmodel

import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelResource
import com.joohnq.user.impl.ui.resource.MedicationsSupplementsResource
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource
import com.joohnq.user.impl.ui.resource.ProfessionalHelpResource

sealed interface OnboardingIntent {
    data class UpdateSleepQuality(val sleepQuality: SleepQualityResource) :
        OnboardingIntent

    data class UpdateStressLevel(val stressLevel: StressLevelResource) : OnboardingIntent
    data class UpdateMood(val mood: MoodResource) : OnboardingIntent
    data class UpdateUserMedicationsSupplements(val medicationsSupplements: MedicationsSupplementsResource?) :
        OnboardingIntent

    data class UpdateUserPhysicalSymptoms(val physicalSymptoms: PhysicalSymptomsResource?) :
        OnboardingIntent

    data class UpdateUserSoughtHelp(val soughtHelp: ProfessionalHelpResource?) :
        OnboardingIntent

    data class UpdateMoodRecordDescription(val description: String) : OnboardingIntent
    data class UpdateSliderValue(val sliderValue: Float) : OnboardingIntent
    data class SetOnboardingStateForTesting(val onboardingState: OnboardingState) :
        OnboardingIntent
}