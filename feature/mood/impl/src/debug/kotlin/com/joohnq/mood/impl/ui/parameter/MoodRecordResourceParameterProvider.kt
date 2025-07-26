package com.joohnq.mood.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodRecordResource

class MoodRecordResourceParameterProvider : PreviewParameterProvider<MoodRecordResource> {
    override val values =
        sequenceOf(
            MoodRecordResource.moodRecordResourceDepressedPreview,
            MoodRecordResource.moodRecordResourceSadPreview,
            MoodRecordResource.moodRecordResourceNeutralPreview,
            MoodRecordResource.moodRecordResourceHappyPreview,
            MoodRecordResource.moodRecordResourceOverjoyedPreview
        )
}
