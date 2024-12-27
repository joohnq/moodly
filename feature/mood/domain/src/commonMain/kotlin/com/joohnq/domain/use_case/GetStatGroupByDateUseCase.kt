package com.joohnq.domain.use_case

import com.joohnq.domain.entity.StatsRecord
import kotlinx.datetime.LocalDate

class GetStatGroupByDateUseCase {
    operator fun invoke(
        statsRecords: List<StatsRecord>,
        formatKey: (LocalDate) -> String
    ): Map<String, List<StatsRecord>> =
        statsRecords
            .groupBy { it.date.date }
            .map { (key, value) ->
                formatKey(key) to value
            }
            .toMap()
}