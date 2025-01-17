package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.StatsRecord
import kotlinx.datetime.LocalDate

class GetStatGroupByDateUseCase {
    operator fun invoke(
        statsRecords: List<StatsRecord>,
    ): Map<LocalDate, List<StatsRecord>> =
        statsRecords
            .groupBy { it.createdAt }
            .map { (key, value) ->
                key.date to value
            }.toMap()
}