package com.joohnq.onboarding.ui.viewmodel

import com.joohnq.core.ui.entity.Time
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.user.ui.resource.MedicationsSupplementsResource
import com.joohnq.user.ui.resource.PhysicalSymptomsResource
import com.joohnq.user.ui.resource.ProfessionalHelpResource

data class OnboardingState(
    val physicalSymptoms: PhysicalSymptomsResource? = null,
    val soughtHelp: ProfessionalHelpResource? = null,
    val medicationsSupplements: MedicationsSupplementsResource? = null,
    val moodRecord: MoodRecord = MoodRecord(),
    val sleepQuality: SleepQualityResource = SleepQualityResource.Worst,
    val startSleepTime: Time = Time(0, 0),
    val endSleepTime: Time = Time(0, 0),
    val stressLevel: StressLevelResource = StressLevelResource.Three,
    val sliderValue: Float = 0f,
)