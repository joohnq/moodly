package com.joohnq.mood.impl.ui.fake

import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.mood.impl.ui.resource.MoodResource

val depressedMoodRecordResourcePreview = MoodRecordResource(
    id = MoodResource.Depressed.id,
    mood = MoodResource.Depressed,
    description = "Description",
)

val sadMoodRecordResourcePreview = MoodRecordResource(
    id = MoodResource.Sad.id,
    mood = MoodResource.Sad,
    description = "Description",
)

val neutralMoodRecordResourcePreview = MoodRecordResource(
    id = MoodResource.Happy.id,
    mood = MoodResource.Neutral,
    description = "Description",
)

val happyMoodRecordResourcePreview = MoodRecordResource(
    id = MoodResource.Happy.id,
    mood = MoodResource.Happy,
    description = "Description",
)

val overjoyedMoodRecordResourcePreview = MoodRecordResource(
    id = MoodResource.Overjoyed.id,
    mood = MoodResource.Overjoyed,
    description = "Description",
)

val moodRecordsResourcesListPreview = listOf(
    depressedMoodRecordResourcePreview,
    sadMoodRecordResourcePreview,
    neutralMoodRecordResourcePreview,
    happyMoodRecordResourcePreview,
    overjoyedMoodRecordResourcePreview,
)