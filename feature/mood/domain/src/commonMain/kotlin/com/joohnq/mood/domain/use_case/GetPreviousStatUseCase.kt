package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.MoodRecord

class GetPreviousStatUseCase {
    operator fun invoke(record: MoodRecord, records: List<MoodRecord>): MoodRecord? =
        records.filter { it.createdAt < record.createdAt }
            .maxByOrNull { it.createdAt }
}