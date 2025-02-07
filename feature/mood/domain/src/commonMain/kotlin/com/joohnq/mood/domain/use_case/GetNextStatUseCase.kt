package com.joohnq.mood.domain.use_case

import com.joohnq.mood.domain.entity.MoodRecord

class GetNextStatUseCase {
    operator fun invoke(record: MoodRecord, moodRecords: List<MoodRecord>): MoodRecord? =
        moodRecords.filter { it.createdAt > record.createdAt }
            .minByOrNull { it.createdAt }
}