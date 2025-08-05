package com.joohnq.sleep_quality.api.use_case

import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.repository.SleepQualityRepository

class AddSleepQualityUseCase(
    private val repository: SleepQualityRepository,
) {
    suspend operator fun invoke(sleepQuality: SleepQualityRecord): Result<Boolean> = repository.add(sleepQuality)
}