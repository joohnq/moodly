package com.joohnq.sleep_quality.domain.use_case

import com.joohnq.shared.domain.toResult
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository

class AddSleepQualityUseCase(private val sleepQualityRepository: SleepQualityRepository) {
    suspend operator fun invoke(sleepQuality: SleepQualityRecord): Result<Boolean> =
        sleepQualityRepository.addSleepQuality(sleepQuality).toResult()
}