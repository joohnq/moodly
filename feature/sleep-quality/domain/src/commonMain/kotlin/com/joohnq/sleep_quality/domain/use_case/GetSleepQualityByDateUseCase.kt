package com.joohnq.sleep_quality.domain.use_case

import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import kotlinx.datetime.LocalDate

class GetSleepQualityByDateUseCase(private val sleepQualityRepository: SleepQualityRepository) {
    suspend operator fun invoke(date: LocalDate): Result<SleepQualityRecord?> =
        sleepQualityRepository.getSleepQualityByDate(date)
}