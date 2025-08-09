package com.joohnq.self_journal.impl.ui.mapper

import com.joohnq.api.getNow
import com.joohnq.api.mapper.YearMapper.getTotalDays
import com.joohnq.mood.add.ui.mapper.MoodAverageResourceMapper.toResource
import com.joohnq.mood.add.ui.mapper.MoodResourceMapper.toDomain
import com.joohnq.mood.add.ui.mapper.MoodResourceMapper.toResource
import com.joohnq.mood.add.ui.resource.MoodAverageResource
import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.mood.api.mapper.MoodAverageMapper.toAverage
import com.joohnq.self_journal.api.entity.SelfJournalRecord
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

object SelfJournalRecordResourceMapper {
    fun SelfJournalRecordResource.toDomain(): SelfJournalRecord =
        SelfJournalRecord(
            id = id,
            mood = mood.toDomain(),
            title = title,
            description = description,
            createdAt = createdAt
        )

    fun List<SelfJournalRecordResource>.getTodaySelfJournalRecord(): SelfJournalRecordResource? =
        find {
            it.createdAt.date ==
                getNow().date
        }

    fun List<SelfJournalRecordResource>.getGrouped(): List<Pair<MoodResource, Int>> =
        groupBy { it.mood }
            .map { it.key to it.value.size }
            .sortedBy { it.first.id }

    fun List<SelfJournalRecordResource>.calculateSelfJournalsAverage(): MoodAverageResource {
        if (isEmpty()) return MoodAverageResource.Skipped
        val score = sumOf { it.mood.healthLevel } / size
        return score.toAverage().toResource()
    }

    fun List<SelfJournalRecordResource?>.getSelfJournalsInYear(date: LocalDateTime = getNow()): String {
        val days =
            filter { it?.createdAt?.year == date.year }
                .associateBy { it?.createdAt?.date }
                .keys.size
        return "$days/${date.year.getTotalDays()}"
    }

    fun SelfJournalRecord.toResource(): SelfJournalRecordResource =
        SelfJournalRecordResource(
            id = id,
            mood = mood.toResource(),
            title = title,
            description = description,
            createdAt = createdAt
        )

    fun List<SelfJournalRecord>.toResource(): List<SelfJournalRecordResource> = map { it.toResource() }

    fun List<SelfJournalRecordResource>.toGroupedByDate(): Map<LocalDate, List<SelfJournalRecordResource>> =
        groupBy { it.createdAt }
            .map { (key, value) ->
                key.date to value
            }.toMap()
}
