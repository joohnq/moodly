package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository
import com.joohnq.shared.domain.toResult

class AddStatsUseCase(private val statsRepository: StatsRepository) {
    suspend operator fun invoke(statsRecord: StatsRecord): Result<Boolean> =
        statsRepository.addStats(statsRecord).toResult()
}