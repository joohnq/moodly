package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository

class GetStatsUseCase(private val statsRepository: StatsRepository) {
    suspend operator fun invoke(): Result<List<StatsRecord>> = statsRepository.getStats()
}