package com.joohnq.mood.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.mood.add.ui.resource.MoodRecordResource

class ListMoodRecordResourceParameterProvider : PreviewParameterProvider<List<MoodRecordResource>> {
    override val values =
        sequenceOf(
            MoodRecordResource.allMoodRecordResourcePreview,
            emptyList()
        )
}
