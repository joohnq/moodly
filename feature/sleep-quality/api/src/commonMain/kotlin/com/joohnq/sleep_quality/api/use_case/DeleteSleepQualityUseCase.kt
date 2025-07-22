package com.joohnq.sleep_quality.api.use_case

import com.joohnq.sleep_quality.api.repository.SleepQualityRepository

class DeleteSleepQualityUseCase(private val sleepQualityRepository: SleepQualityRepository) {
    suspend operator fun invoke(id: Int): Result<Boolean> =
        sleepQualityRepository.deleteSleepQuality(id)
}