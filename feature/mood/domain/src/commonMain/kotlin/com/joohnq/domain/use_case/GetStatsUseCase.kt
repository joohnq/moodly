package com.joohnq.domain.use_case

import com.joohnq.domain.entity.StatsRecord
import com.joohnq.domain.repository.StatsRepository

class GetStatsUseCase(private val statsRepository: StatsRepository) {
    suspend operator fun invoke(): List<StatsRecord> = statsRepository.getStats()
}