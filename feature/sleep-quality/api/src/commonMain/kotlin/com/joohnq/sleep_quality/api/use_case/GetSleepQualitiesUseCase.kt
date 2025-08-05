package com.joohnq.sleep_quality.api.use_case

import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.repository.SleepQualityRepository

class GetSleepQualitiesUseCase(
    private val repository: SleepQualityRepository,
) {
    suspend operator fun invoke(): Result<List<SleepQualityRecord>> = repository.records.value
}