package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.StatsRecord

class GetNextStatUseCase {
    operator fun invoke(statsRecord: StatsRecord, statsRecords: List<StatsRecord>): StatsRecord? =
        statsRecords.filter { it.createdAt > statsRecord.createdAt }
            .minByOrNull { it.createdAt }
}