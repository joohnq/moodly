package com.joohnq.onboarding.ui.viewmodel

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.user.ui.resource.MedicationsSupplementsResource
import com.joohnq.user.ui.resource.PhysicalSymptomsResource
import com.joohnq.user.ui.resource.ProfessionalHelpResource

data class OnboardingViewModelState(
    val physicalSymptoms: PhysicalSymptomsResource? = null,
    val soughtHelp: ProfessionalHelpResource? = null,
    val medicationsSupplements: MedicationsSupplementsResource? = null,
    val statsRecord: StatsRecord = StatsRecord(),
    val sleepQuality: SleepQualityResource = SleepQualityResource.Worst,
    val stressLevel: StressLevelResource = StressLevelResource.Three,
    val sliderValue: Float = 0f,
)