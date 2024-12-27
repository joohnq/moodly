package com.joohnq.onboarding.ui.viewmodel

import com.joohnq.domain.entity.MedicationsSupplements
import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.StatsRecord
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.stress_level.domain.entity.StressLevel

data class OnboardingViewModelState(
    val physicalSymptoms: PhysicalSymptoms? = null,
    val soughtHelp: ProfessionalHelp? = null,
    val medicationsSupplements: MedicationsSupplements? = null,
    val statsRecord: StatsRecord = StatsRecord.init(),
    val sleepQuality: SleepQuality = SleepQuality.Worst,
    val stressLevel: StressLevel = StressLevel.Three,
    val sliderValue: Float = 0f
)