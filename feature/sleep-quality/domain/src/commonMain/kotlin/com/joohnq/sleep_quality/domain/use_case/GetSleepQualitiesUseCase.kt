package com.joohnq.sleep_quality.domain.use_case

import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository

class GetSleepQualitiesUseCase(private val repository: SleepQualityRepository) {
    suspend operator fun invoke(): List<SleepQualityRecord> = repository.getSleepQualities()
}