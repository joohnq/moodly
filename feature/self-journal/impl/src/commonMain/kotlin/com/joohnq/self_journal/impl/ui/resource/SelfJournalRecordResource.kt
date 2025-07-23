package com.joohnq.self_journal.impl.ui.resource

import com.joohnq.api.getNow
import com.joohnq.mood.impl.ui.resource.MoodResource
import kotlinx.datetime.LocalDateTime

data class SelfJournalRecordResource(
    val id: Int = -1,
    val mood: MoodResource = MoodResource.Neutral,
    val title: String = "",
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
) {
    companion object {
        val selfJournalRecordResourceDepressedPreview = SelfJournalRecordResource(
            id = MoodResource.Depressed.id,
            mood = MoodResource.Depressed,
            title = "Title",
            description = "Description",
        )

        val selfJournalRecordResourceSadPreview = SelfJournalRecordResource(
            id = MoodResource.Sad.id,
            mood = MoodResource.Sad,
            title = "Title",
            description = "Description",
        )

        val selfJournalRecordResourceNeutralPreview = SelfJournalRecordResource(
            id = MoodResource.Neutral.id,
            mood = MoodResource.Neutral,
            title = "Title",
            description = "Description",
        )

        val selfJournalRecordResourceHappyPreview = SelfJournalRecordResource(
            id = MoodResource.Happy.id,
            mood = MoodResource.Happy,
            title = "Title",
            description = "Description",
        )

        val selfJournalRecordResourceOverjoyedPreview = SelfJournalRecordResource(
            id = MoodResource.Overjoyed.id,
            mood = MoodResource.Overjoyed,
            title = "Title",
            description = "Description",
        )

        val allSelfJournalRecordResourcePreview = listOf(
            selfJournalRecordResourceDepressedPreview,
            selfJournalRecordResourceSadPreview,
            selfJournalRecordResourceNeutralPreview,
            selfJournalRecordResourceHappyPreview,
            selfJournalRecordResourceOverjoyedPreview,
        )
    }
}