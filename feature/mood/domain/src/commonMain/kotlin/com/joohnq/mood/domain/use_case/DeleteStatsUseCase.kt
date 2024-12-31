package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.repository.StatsRepository
import org.koin.core.annotation.Factory

@Factory
class DeleteStatsUseCase(private val statsRepository: StatsRepository) {
    suspend operator fun invoke(id: Int): Boolean = statsRepository.deleteStat(id)
}