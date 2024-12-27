package com.joohnq.domain.use_case

import com.joohnq.domain.entity.StatsRecord

class GetNextStatUseCase {
    operator fun invoke(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.filter { it.date > statsRecord.date }
            .minByOrNull { it.date }
}