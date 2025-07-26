package com.joohnq.self_journal.api.entity

import com.joohnq.api.getNow
import com.joohnq.mood.api.entity.Mood
import kotlinx.datetime.LocalDateTime

data class SelfJournalRecord(
    val id: Int = -1,
    val mood: Mood = Mood.Neutral,
    val title: String = "",
    val description: String = "",
    val createdAt: LocalDateTime = getNow()
) {
    companion object {
        val selfJournalDepressedPreview =
            SelfJournalRecord(
                id = Mood.Depressed.id,
                mood = Mood.Depressed,
                title = "Title",
                description = "Description"
            )

        val selfJournalSadPreview =
            SelfJournalRecord(
                id = Mood.Sad.id,
                mood = Mood.Sad,
                title = "Title",
                description = "Description"
            )

        val selfJournalNeutralPreview =
            SelfJournalRecord(
                id = Mood.Neutral.id,
                mood = Mood.Neutral,
                title = "Title",
                description = "Description"
            )

        val selfJournalHappyPreview =
            SelfJournalRecord(
                id = Mood.Happy.id,
                mood = Mood.Happy,
                title = "Title",
                description = "Description"
            )

        val selfJournalOverjoyedPreview =
            SelfJournalRecord(
                id = Mood.Overjoyed.id,
                mood = Mood.Overjoyed,
                title = "Title",
                description = "Description"
            )
    }
}
