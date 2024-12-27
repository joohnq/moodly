package com.joohnq.domain.use_case

import com.joohnq.domain.repository.StatsRepository

class DeleteStatsUseCase(private val statsRepository: StatsRepository) {
    suspend operator fun invoke(id: Int): Boolean = statsRepository.deleteStat(id)
}