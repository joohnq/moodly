package com.joohnq.sleep_quality.domain.use_case

import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository

class DeleteSleepQualityUseCase(private val sleepQualityRepository: SleepQualityRepository) {
    suspend operator fun invoke(id: Int): Result<Boolean> =
        sleepQualityRepository.deleteSleepQuality(id)
}