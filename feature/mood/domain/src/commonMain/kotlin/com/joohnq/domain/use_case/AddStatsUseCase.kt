package com.joohnq.domain.use_case

import com.joohnq.domain.entity.StatsRecord
import com.joohnq.domain.repository.StatsRepository

class AddStatsUseCase(private val statsRepository: StatsRepository) {
    suspend operator fun invoke(statsRecord: StatsRecord): Boolean =
        statsRepository.addStats(statsRecord)
}