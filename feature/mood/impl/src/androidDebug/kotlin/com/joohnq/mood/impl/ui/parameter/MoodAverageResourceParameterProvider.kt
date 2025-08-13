package com.joohnq.mood.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.mood.add.ui.resource.MoodAverageResource

class MoodAverageResourceParameterProvider : PreviewParameterProvider<MoodAverageResource> {
    override val values =
        sequenceOf(
            MoodAverageResource.Neutral,
            MoodAverageResource.Negative,
            MoodAverageResource.Skipped,
            MoodAverageResource.Positive
        )
}
