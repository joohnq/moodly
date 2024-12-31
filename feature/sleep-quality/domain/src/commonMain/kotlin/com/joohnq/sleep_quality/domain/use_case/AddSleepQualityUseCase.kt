package com.joohnq.sleep_quality.domain.use_case

import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import org.koin.core.annotation.Factory

@Factory
class AddSleepQualityUseCase(private val sleepQualityRepository: SleepQualityRepository) {
    suspend operator fun invoke(sleepQuality: SleepQualityRecord): Boolean =
        sleepQualityRepository.addSleepQuality(sleepQuality)
}