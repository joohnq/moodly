package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.domain.repository.StatsRepository
import kotlinx.datetime.LocalDate

class GetStatsByDate(private val statsRepository: StatsRepository) {
    suspend operator fun invoke(date: LocalDate): Result<StatsRecord?> =
        statsRepository.getStatByDate(date)
}
