package com.joohnq.sleep_quality.api.use_case

import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.repository.SleepQualityRepository
import kotlinx.coroutines.flow.Flow

class GetSleepQualitiesUseCase(
    private val repository: SleepQualityRepository,
) {
    operator fun invoke(): Flow<List<SleepQualityRecord>> = repository.observe()
}
