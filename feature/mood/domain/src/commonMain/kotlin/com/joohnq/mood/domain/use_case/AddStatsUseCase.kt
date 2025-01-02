package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository


class AddStatsUseCase(private val statsRepository: StatsRepository) {
    suspend operator fun invoke(statsRecord: StatsRecord): Boolean =
        statsRepository.addStats(statsRecord)
}