package com.joohnq.mood.domain.use_case

import com.joohnq.domain.IDatetimeProvider
import com.joohnq.mood.domain.entity.StatsRecord
import org.koin.core.annotation.Factory

@Factory
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