package com.joohnq.onboarding.ui.viewmodel

import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import com.joohnq.user.ui.resource.MedicationsSupplementsResource
import com.joohnq.user.ui.resource.PhysicalSymptomsResource
import com.joohnq.user.ui.resource.ProfessionalHelpResource

data class OnboardingState(
    val physicalSymptoms: PhysicalSymptomsResource? = null,
    val soughtHelp: ProfessionalHelpResource? = null,
    val medicationsSupplements: MedicationsSupplementsResource? = null,
    val moodRecord: MoodRecordResource = MoodRecordResource(),
    val sleepQuality: SleepQualityRecordResource = SleepQualityRecordResource(),
    val stressLevel: StressLevelRecordResource = StressLevelRecordResource(),
    val sliderValue: Float = 0f,
)