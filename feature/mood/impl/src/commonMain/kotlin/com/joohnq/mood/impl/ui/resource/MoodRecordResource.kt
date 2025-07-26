package com.joohnq.mood.impl.ui.resource

import com.joohnq.api.getNow
import kotlinx.datetime.LocalDateTime

data class MoodRecordResource(
    val id: Int = -1,
    val mood: MoodResource = MoodResource.Neutral,
    val description: String = "",
    val createdAt: LocalDateTime = getNow(),
) {
    companion object {
        val moodRecordResourceDepressedPreview =
            MoodRecordResource(
                id = MoodResource.Depressed.id,
                mood = MoodResource.Depressed,
                description = "Description"
            )

        val moodRecordResourceSadPreview =
            MoodRecordResource(
                id = MoodResource.Sad.id,
                mood = MoodResource.Sad,
                description = "Description"
            )

        val moodRecordResourceNeutralPreview =
            MoodRecordResource(
                id = MoodResource.Neutral.id,
                mood = MoodResource.Neutral,
                description = "Description"
            )

        val moodRecordResourceHappyPreview =
            MoodRecordResource(
                id = MoodResource.Happy.id,
                mood = MoodResource.Happy,
                description = "Description"
            )

        val moodRecordResourceOverjoyedPreview =
            MoodRecordResource(
                id = MoodResource.Overjoyed.id,
                mood = MoodResource.Overjoyed,
                description = "Description"
            )

        val allMoodRecordResourcePreview =
            listOf(
                moodRecordResourceDepressedPreview,
                moodRecordResourceSadPreview,
                moodRecordResourceNeutralPreview,
                moodRecordResourceHappyPreview,
                moodRecordResourceOverjoyedPreview
            )
    }
}
