package com.joohnq.sleep_quality.domain.use_case

import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository


class GetSleepQualitiesUseCase(private val sleepQualityRepository: SleepQualityRepository) {
    suspend operator fun invoke(): List<SleepQualityRecord> =
        sleepQualityRepository.getSleepQualities()
}