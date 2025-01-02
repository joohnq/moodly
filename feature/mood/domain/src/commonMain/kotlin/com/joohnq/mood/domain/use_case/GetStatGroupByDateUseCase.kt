package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.shared.domain.IDatetimeProvider

class GetStatGroupByDateUseCase(private val dateTimeProvider: IDatetimeProvider) {
    operator fun invoke(
        statsRecords: List<StatsRecord>,
    ): Map<String, List<StatsRecord>> =
        statsRecords
            .groupBy { it.date.date }
            .map { (key, value) ->
                dateTimeProvider.formatDate(key) to value
            }
            .toMap()
}