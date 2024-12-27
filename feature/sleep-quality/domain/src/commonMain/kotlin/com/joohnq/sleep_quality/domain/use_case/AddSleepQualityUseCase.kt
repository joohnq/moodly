package com.joohnq.sleep_quality.domain.use_case

import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository

class AddSleepQualityUseCase(private val repository: SleepQualityRepository) {
    suspend operator fun invoke(sleepQuality: SleepQualityRecord): Boolean =
        repository.addSleepQuality(sleepQuality)
}