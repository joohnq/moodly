package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.MoodRecord
import kotlinx.datetime.LocalDate

class GetStatGroupByDateUseCase {
    operator fun invoke(
        records: List<MoodRecord>,
    ): Map<LocalDate, List<MoodRecord>> =
        records
            .groupBy { it.createdAt }
            .map { (key, value) ->
                key.date to value
            }.toMap()
}