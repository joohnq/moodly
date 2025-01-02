package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.repository.StatsRepository
import com.joohnq.shared.domain.toResult

class DeleteStatsUseCase(private val statsRepository: StatsRepository) {
    suspend operator fun invoke(id: Int): Result<Boolean> =
        statsRepository.deleteStat(id).toResult()
}