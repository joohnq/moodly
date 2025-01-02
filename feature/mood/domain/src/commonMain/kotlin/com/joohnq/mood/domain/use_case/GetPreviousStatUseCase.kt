package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.StatsRecord

class GetPreviousStatUseCase {
    operator fun invoke(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.filter { it.date < statsRecord.date }
            .maxByOrNull { it.date }
}